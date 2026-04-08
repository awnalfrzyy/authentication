package strigops.account.internal.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import strigops.account.features.auth.otp.dto.VerifyOtpRequest;
import strigops.account.features.auth.otp.OtpService;
import strigops.account.features.auth.register.UserRegistrationService;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class OtpController {

    @Autowired
    private OtpService otpService;
    @Autowired
    private UserRegistrationService registrationService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyOtpRequest request){
        boolean isValid = otpService.verifyOtp(
                request.email(),
                request.otp()
                );

        if(!isValid){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("OTP is wrong or your code has expired");
        }

        registrationService.enableUser(request.email());
        return ResponseEntity.ok("Verification successful, account is now active!");
    }
}
