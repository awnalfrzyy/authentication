package strigops.account.ui.screens

import kotlinx.html.*
import strigops.account.ui.assets.*
import strigops.account.ui.components.*
import strigops.account.ui.components.Alert
import strigops.account.ui.layouts.*

fun HTML.sendOtpPage(email: String? = null, errorMessage: String? = null) {
    mainLayout(title = "Send Verification Code - StrigoAccount") {
        div(
                classes =
                        "min-h-screen flex flex-col items-center justify-center bg-gray-50 px-4 py-12 sm:px-6 lg:px-8"
        ) {
            div(classes = "w-full max-w-md space-y-8") {
                div(classes = "text-center") {
                    img(
                            classes = "mx-auto h-12 w-auto",
                            src =
                                    "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%234285f4'%3E%3Cpath d='M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z'/%3E%3Cpath d='M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z' fill='%2334a853'/%3E%3Cpath d='M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z' fill='%23fbbc05'/%3E%3Cpath d='M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z' fill='%23ea4335'/%3E%3C/svg%3E",
                            alt = "Strigo Account"
                    )
                    h2(classes = "mt-6 text-3xl font-extrabold text-gray-900") {
                        +"Send verification code"
                    }
                    p(classes = "mt-2 text-sm text-gray-600") {
                        +"Enter your email to receive a verification code"
                    }
                }

                errorMessage?.let { Alert(it, type = AlertType.ERROR) }

                form(
                        action = "/auth/send-otp",
                        method = FormMethod.post,
                        classes = "mt-8 space-y-6"
                ) {
                    input(type = InputType.hidden, name = "_csrf") {}

                    Input(
                            label = "Email address",
                            name = "email",
                            inputType = InputType.email,
                            icon = StrigoIcon.EMAIL,
                            placeholder = "Enter your email",
                            value = email ?: ""
                    )

                    Button(
                            text = "Send verification code",
                            variant = ButtonVariant.PRIMARY,
                            type = CustomButtonType.submit,
                            fullWidth = true
                    )
                }

                div(classes = "mt-6 text-center") {
                    a(
                            href = "/auth/login",
                            classes = "font-medium text-blue-600 hover:text-blue-500"
                    ) { +"Back to sign in" }
                }
            }
        }
    }
}

fun HTML.otpVerificationPage(email: String, errorMessage: String? = null) {
    mainLayout(title = "Verify your email - StrigoAccount") {
        div(
                classes =
                        "min-h-screen flex flex-col items-center justify-center bg-gray-50 px-4 py-12 sm:px-6 lg:px-8"
        ) {
            div(classes = "w-full max-w-md space-y-8") {
                div(classes = "text-center") {
                    img(
                            classes = "mx-auto h-12 w-auto",
                            src =
                                    "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%234285f4'%3E%3Cpath d='M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z'/%3E%3Cpath d='M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z' fill='%2334a853'/%3E%3Cpath d='M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z' fill='%23fbbc05'/%3E%3Cpath d='M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z' fill='%23ea4335'/%3E%3C/svg%3E",
                            alt = "Strigo Account"
                    )
                    h2(classes = "mt-6 text-3xl font-extrabold text-gray-900") {
                        +"Verify your email"
                    }
                    p(classes = "mt-2 text-sm text-gray-600") {
                        +"We sent a verification code to "
                        span(classes = "font-medium") { +email }
                    }
                }

                errorMessage?.let { Alert(it, type = AlertType.ERROR) }

                div(classes = "bg-blue-50 border border-blue-200 rounded-lg p-4 mb-8") {
                    div(classes = "flex") {
                        div(classes = "flex-shrink-0") {
                            strigoIcon(StrigoIcon.EMAIL, size = 20, colorClass = "text-blue-400")
                        }
                        div(classes = "ml-3") {
                            h3(classes = "text-sm font-medium text-blue-800") {
                                +"Check your email"
                            }
                            div(classes = "mt-2 text-sm text-blue-700") {
                                p {
                                    +"Enter the 6-digit code we sent to your email address. The code will expire in 10 minutes."
                                }
                            }
                        }
                    }
                }

                form(action = "/auth/verify", method = FormMethod.post, classes = "space-y-6") {
                    input(type = InputType.hidden, name = "email") { this.value = email }
                    input(type = InputType.hidden, name = "_csrf") {}

                    div(classes = "space-y-4") {
                        label(classes = "block text-sm font-medium text-gray-700") {
                            +"Verification code"
                        }
                        div(classes = "flex space-x-2") {
                            for (i in 1..6) {
                                input(
                                        type = InputType.text,
                                        name = "otp$i",
                                        classes =
                                                "w-12 h-12 text-center text-lg font-semibold border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 otp-input"
                                ) {
                                    attributes["inputmode"] = "numeric"
                                    attributes["pattern"] = "[0-9]"
                                    attributes["autocomplete"] = "one-time-code"
                                    attributes["maxlength"] = "1"
                                    attributes["required"] = ""
                                    id = "otp$i"
                                }
                            }
                        }
                    }

                    div(classes = "mt-6") {
                        Button(
                                text = "Verify code",
                                variant = ButtonVariant.PRIMARY,
                                type = CustomButtonType.submit,
                                fullWidth = true
                        )
                    }
                }

                div(classes = "mt-6 text-center") {
                    p(classes = "text-sm text-gray-600") {
                        +"Didn't receive the code? "
                        a(
                                href = "/auth/send-otp?email=$email",
                                classes = "font-medium text-blue-600 hover:text-blue-500"
                        ) { +"Resend code" }
                    }
                }

                div(classes = "mt-4 text-center") {
                    a(href = "/auth/login", classes = "text-sm text-gray-500 hover:text-gray-700") {
                        +"Back to sign in"
                    }
                }
            }
        }
    }
}
