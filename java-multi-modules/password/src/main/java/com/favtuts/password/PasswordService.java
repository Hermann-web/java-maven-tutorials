package com.favtuts.password;

public interface PasswordService {
    
    String hash(String input);

    String algorithm();
    
}
