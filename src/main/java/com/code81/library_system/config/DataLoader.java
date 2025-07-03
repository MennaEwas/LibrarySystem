package com.code81.library_system.config;

import com.code81.library_system.entities.*;
import com.code81.library_system.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final BookService bookService;
    private final MemberService memberService;

    public DataLoader(UserService userService, BookService bookService, MemberService memberService) {
        this.userService = userService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if (!userService.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@library.com");
            admin.setRole(UserRole.ADMINISTRATOR);
            userService.createUser(admin);
        }

        if (!userService.existsByUsername("librarian")) {
            User librarian = new User();
            librarian.setUsername("librarian");
            librarian.setPassword("lib123");
            librarian.setFirstName("John");
            librarian.setLastName("Librarian");
            librarian.setEmail("librarian@library.com");
            librarian.setRole(UserRole.LIBRARIAN);
            userService.createUser(librarian);
        }

        if (!userService.existsByUsername("staff")) {
            User staff = new User();
            staff.setUsername("staff");
            staff.setPassword("staff123");
            staff.setFirstName("Jane");
            staff.setLastName("Staff");
            staff.setEmail("staff@library.com");
            staff.setRole(UserRole.STAFF);
            userService.createUser(staff);
        }

        System.out.println("Sample users created successfully!");
    }
}