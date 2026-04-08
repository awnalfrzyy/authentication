package strigops.account.ui.presentation

import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import strigops.account.ui.screens.*

@Controller
class AuthViewController {

    @GetMapping("/auth/login")
    @ResponseBody
    fun showLoginPage(@RequestParam(value = "error", required = false) error: String?): String {
        return buildString { appendHTML().html { loginPage(error) } }
    }

    @GetMapping("/auth/register")
    @ResponseBody
    fun showRegisterPage(
            @RequestParam(value = "error", required = false) error: String?,
            @RequestParam(value = "success", required = false) success: String?
    ): String {
        return buildString {
            appendHTML().html { registerPage(errorMessage = error, successMessage = success) }
        }
    }

    @GetMapping("/auth/verify")
    @ResponseBody
    fun showVerifyPage(
            @RequestParam(value = "email", required = true) email: String,
            @RequestParam(value = "error", required = false) error: String?
    ): String {
        return buildString {
            appendHTML().html { otpVerificationPage(email = email, errorMessage = error) }
        }
    }

    @GetMapping("/auth/forgot")
    @ResponseBody
    fun showForgotPasswordPage(
            @RequestParam(value = "error", required = false) error: String?,
            @RequestParam(value = "success", required = false) success: String?
    ): String {
        return buildString {
            appendHTML().html { forgotPasswordPage(errorMessage = error, successMessage = success) }
        }
    }

    @GetMapping("/auth/reset")
    @ResponseBody
    fun showResetPasswordPage(
            @RequestParam(value = "token", required = true) token: String,
            @RequestParam(value = "error", required = false) error: String?
    ): String {
        return buildString {
            appendHTML().html { resetPasswordPage(token = token, errorMessage = error) }
        }
    }

    @GetMapping("/auth/send-otp")
    @ResponseBody
    fun showSendOtpPage(@RequestParam(value = "email", required = false) email: String?): String {
        return buildString { appendHTML().html { sendOtpPage(email = email) } }
    }

    @GetMapping("/auth/change-password")
    @ResponseBody
    fun showChangePasswordPage(
            @RequestParam(value = "error", required = false) error: String?,
            @RequestParam(value = "success", required = false) success: String?
    ): String {
        return buildString {
            appendHTML().html { changePasswordPage(errorMessage = error, successMessage = success) }
        }
    }
}
