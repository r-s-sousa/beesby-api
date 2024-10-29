package com.beesby.api.services;

import com.beesby.api.configurations.AllowedFilters;
import com.beesby.api.entities.Address;
import com.beesby.api.entities.User;
import com.beesby.api.enums.UserStatus;
import com.beesby.api.exceptions.BadRequestException;
import com.beesby.api.exceptions.ConflictException;
import com.beesby.api.exceptions.NotFoundException;
import com.beesby.api.repositories.UserRepository;
import com.beesby.api.requests.AddressRequest;
import com.beesby.api.requests.UserRequest;
import com.beesby.api.requests.UserUpdateRequest;
import com.beesby.api.responses.AddressResponse;
import com.beesby.api.responses.UserResponse;
import com.beesby.api.specifications.GenericSpecification;
import com.beesby.api.utils.FilterItem;
import com.beesby.api.utils.Message;
import com.beesby.api.utils.Paginate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void delete(UUID id, UUID removedBy) {
        User user = findById(id);
        user.setStatus(UserStatus.REMOVED.toString());
        user.setDeletedAt(LocalDateTime.now());
        user.setDeletedBy(removedBy);
        userRepository.save(user);
    }

    public Page<User> findAll(Integer page, Integer size, List<FilterItem> filters) {
        Pageable pageable = Paginate.from(page, size);
        GenericSpecification<User> specification = GenericSpecification.from(filters, AllowedFilters.user());
        return userRepository.findAll(specification, pageable);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.RESOURCE_NOT_FOUND));
    }

    public User save(User user) {
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new BadRequestException(Message.RESOURCE_CONFLICT);
        }

        return userRepository.save(user);
    }

    public User save(UserRequest userRequest, UUID createdBy) {
        if (userRepository.existsByCpf(userRequest.getCpf())) {
            throw new BadRequestException(Message.RESOURCE_CONFLICT);
        }

        User user = User.builder()
                .cpf(userRequest.getCpf())
                .name(userRequest.getName())
                .birthDate(userRequest.getBirthDate())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .address(mapAddress(userRequest.getAddress()))
                .status(UserStatus.ACTIVE.toString())
                .createdBy(createdBy)
                .updatedBy(createdBy)
                .build();

        return userRepository.save(user);
    }

    public void partialUpdate(UUID id, UserUpdateRequest userUpdateRequest, UUID updatedBy) {
        User user = findById(id);

        if (userUpdateRequest.getCpf() != null) {

            Optional<User> userByCpf = userRepository.findByCpf(userUpdateRequest.getCpf());

            if (userByCpf.isPresent() && !userByCpf.get().getId().equals(id)) {
                throw new ConflictException(Message.RESOURCE_CONFLICT);
            }

            user.setCpf(userUpdateRequest.getCpf());
        }
        if (userUpdateRequest.getName() != null) {
            user.setName(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getBirthDate() != null) {
            user.setBirthDate(userUpdateRequest.getBirthDate());
        }
        if (userUpdateRequest.getAddress() != null) {
            user.setAddress(mapAddress(userUpdateRequest.getAddress()));
        }

        user.setUpdatedBy(updatedBy);

        userRepository.save(user);
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .cpf(user.getCpf())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .updatedAt(user.getUpdatedAt())
                .updatedBy(user.getUpdatedBy())
                .deletedAt(user.getDeletedAt())
                .deletedBy(user.getDeletedBy())
                .address(mapToAddressResponse(user.getAddress()))
                .build();
    }

    public AddressResponse mapToAddressResponse(Address address) {
        if (address == null) {
            return null;
        }

        return AddressResponse.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .build();
    }

    private Address mapAddress(AddressRequest addressRequest) {
        return Address.builder()
                .street(addressRequest.getStreet())
                .number(addressRequest.getNumber())
                .complement(addressRequest.getComplement())
                .neighborhood(addressRequest.getNeighborhood())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .zipCode(addressRequest.getZipCode())
                .build();
    }
}