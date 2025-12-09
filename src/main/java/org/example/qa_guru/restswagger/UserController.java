package org.example.qa_guru.restswagger;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Управление пользователями")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);


    public UserController() {
        // Инициализация тестовыми данными
        User user3 = new User();
        user3.setId(counter.getAndIncrement());
        user3.setName("Алексей Сидоров");
        user3.setEmail("alex@example.com");
        user3.setAge(28);
        users.add(user3);
    }

    @GetMapping
    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
    @ApiResponse(responseCode = "200", description = "Успешный запрос")
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public User getUserById(
            @Parameter(description = "ID пользователя", required = true, example = "1")
            @PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @Operation(summary = "Создать нового пользователя", description = "Создает нового пользователя и возвращает его")
    @ApiResponse(responseCode = "200", description = "Пользователь успешно создан")
    public User createUser(
            @Parameter(description = "Данные нового пользователя", required = true)
            @RequestBody User user) {
        user.setId(counter.getAndIncrement());
        users.add(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public String deleteUser(
            @Parameter(description = "ID пользователя для удаления", required = true, example = "1")
            @PathVariable Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        return removed ? "Пользователь удален" : "Пользователь не найден";
    }
}
