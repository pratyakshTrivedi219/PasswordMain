package com.example.passwordchecker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PasswordController {

    @PostMapping("/checkPassword")
    @ResponseBody
    public Map<String, Object> checkPassword(@RequestParam("password") String password) {
        int charRange = PasswordStrengthChecker.strengthEstimator(password);
        double entropy = PasswordStrengthChecker.entropyCalculator(charRange, password);
        boolean common = PasswordStrengthChecker.checkCommonPasswordFile(password);
        String timeToCrack = TimeToCrack.timeToCrack(entropy);

        String result;
        if (charRange == 0) {
            result = "PASSWORD TOO SHORT";
        } else if (charRange == 1) {
            result = "COMMON PASSWORD, USE A MORE UNIQUE AND SECURE PASSWORD";
        } else {
            if (entropy > 15 && entropy <= 25) {
                result = "WEAK PASSWORD";
            } else if (entropy > 25 && entropy <= 35) {
                result = "MODERATE PASSWORD";
            } else if (entropy > 35 && entropy <= 45) {
                result = "STRONG PASSWORD";
            } else {
                result = "VERY STRONG PASSWORD";
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("password", password); // Hide password for security
        response.put("result", result);
        response.put("timeToCrack", timeToCrack);
        if (!common) {
            response.put("Password is too common", false); // Add if password is commonly used or not
        }
        return response;

        // Helper method to hide password

    }
}