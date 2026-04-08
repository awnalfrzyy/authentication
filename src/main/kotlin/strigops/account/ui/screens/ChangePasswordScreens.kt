package strigops.account.ui.screens

import kotlinx.html.*
import strigops.account.ui.assets.*
import strigops.account.ui.components.*
import strigops.account.ui.components.Alert
import strigops.account.ui.layouts.*

fun HTML.changePasswordPage(errorMessage: String? = null, successMessage: String? = null) {
    mainLayout(title = "Change Password - StrigoAccount") {
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
                        +"Change Password"
                    }
                    p(classes = "mt-2 text-sm text-gray-600") {
                        +"Update your password to keep your account secure"
                    }
                }

                errorMessage?.let { Alert(it, type = AlertType.ERROR) }
                successMessage?.let { Alert(it, type = AlertType.SUCCESS) }

                div(classes = "bg-blue-50 border border-blue-200 rounded-lg p-4 mb-6") {
                    div(classes = "flex") {
                        div(classes = "flex-shrink-0") {
                            strigoIcon(StrigoIcon.PASSWORD, size = 20, colorClass = "text-blue-400")
                        }
                        div(classes = "ml-3") {
                            h3(classes = "text-sm font-medium text-blue-800") {
                                +"Password Requirements"
                            }
                            div(classes = "mt-2 text-sm text-blue-700") {
                                ul(classes = "list-disc pl-5 space-y-1") {
                                    li { +"At least 8 characters long" }
                                    li { +"Contains uppercase and lowercase letters" }
                                    li { +"Contains at least one number" }
                                    li { +"Contains at least one special character" }
                                }
                            }
                        }
                    }
                }

                form(
                        action = "/auth/change-password",
                        method = FormMethod.post,
                        classes = "mt-8 space-y-6"
                ) {
                    input(type = InputType.hidden, name = "_csrf") {}

                    div(classes = "space-y-4") {
                        Input(
                                label = "Current Password",
                                name = "currentPassword",
                                inputType = InputType.password,
                                placeholder = "Enter your current password",
                                required = true
                        )

                        Input(
                                label = "New Password",
                                name = "newPassword",
                                inputType = InputType.password,
                                placeholder = "Enter your new password",
                                required = true
                        )

                        Input(
                                label = "Confirm New Password",
                                name = "confirmPassword",
                                inputType = InputType.password,
                                placeholder = "Confirm your new password",
                                required = true
                        )
                    }

                    div(classes = "mt-6") {
                        Button(
                                text = "Update Password",
                                variant = ButtonVariant.PRIMARY,
                                type = CustomButtonType.submit,
                                fullWidth = true
                        )
                    }
                }

                div(classes = "mt-6 text-center") {
                    a(
                            href = "/auth/login",
                            classes = "font-medium text-blue-600 hover:text-blue-500"
                    ) { +"Back to Dashboard" }
                }
            }
        }
    }
}
