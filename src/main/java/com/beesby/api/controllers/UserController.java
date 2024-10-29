package com.beesby.api.controllers;

import com.beesby.api.entities.User;
import com.beesby.api.requests.UserRequest;
import com.beesby.api.requests.UserUpdateRequest;
import com.beesby.api.responses.UserResponse;
import com.beesby.api.services.AuthService;
import com.beesby.api.services.UserService;
import com.beesby.api.utils.DynamicFilter;
import com.beesby.api.utils.FilterItem;
import com.beesby.api.utils.Paginate;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/v1")
@RestController
@Tag(name = "users", description = "users management")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/users")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest,
                                             UriComponentsBuilder uriComponentsBuilder) {
        UUID createdBy = authService.getMe().getId();

        User user = userService.save(userRequest, createdBy);
        UserResponse userResponse = userService.mapToResponse(user);

        URI uri = uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Paginate<User, UserResponse>> findAll(@RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "") Map<String, String> params) {
        List<FilterItem> filters = DynamicFilter.parseFilters(params);
        Page<User> users = userService.findAll(page, size, filters);

        List<UserResponse> usersResponse = users.map(userService::mapToResponse).getContent();
        return ResponseEntity.ok(Paginate.from(users, usersResponse));
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        User user = userService.findById(id);
        UserResponse userResponse = userService.mapToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable UUID id,
                                              @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        UUID updatedBy = authService.getMe().getId();

        userService.partialUpdate(id, userUpdateRequest, updatedBy);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        UUID removedBy = authService.getMe().getId();

        userService.delete(id, removedBy);
        return ResponseEntity.noContent().build();
    }
}
