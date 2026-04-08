package strigops.account.ui.screens

import kotlinx.html.*
import strigops.account.ui.components.*
import strigops.account.ui.components.Alert
import strigops.account.ui.layouts.*

fun HTML.loginPage(errorMessage: String? = null) {
    mainLayout(title = "Sign in - StrigoAccount") {
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
                        +"Sign in to your account"
                    }
                    p(classes = "mt-2 text-sm text-gray-600") {
                        +"Or "
                        a(
                                href = "/auth/register",
                                classes = "font-medium text-blue-600 hover:text-blue-500"
                        ) { +"create a new account" }
                    }
                }

                if (errorMessage != null) {
                    Alert(errorMessage, type = AlertType.ERROR)
                }

                form(action = "/auth/login", method = FormMethod.post, classes = "mt-8 space-y-6") {
                    input(type = InputType.hidden, name = "_csrf") {}

                    div(classes = "space-y-4") {
                        Input(
                                label = "Email address",
                                name = "email",
                                inputType = InputType.email,
                                placeholder = "Enter your email"
                        )

                        Input(
                                label = "Password",
                                name = "password",
                                inputType = InputType.password,
                                placeholder = "Enter your password"
                        )
                    }

                    div(classes = "flex items-center justify-between") {
                        div(classes = "flex items-center") {
                            input(
                                    type = InputType.checkBox,
                                    name = "remember-me",
                                    classes =
                                            "h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                            ) { id = "remember-me" }
                            label(classes = "ml-2 block text-sm text-gray-900") {
                                htmlFor = "remember-me"
                                +"Remember me"
                            }
                        }

                        div(classes = "text-sm") {
                            a(
                                    href = "/auth/forgot",
                                    classes = "font-medium text-blue-600 hover:text-blue-500"
                            ) { +"Forgot your password?" }
                        }
                    }

                    div(classes = "mt-6") {
                        Button(
                                text = "Sign in",
                                variant = ButtonVariant.PRIMARY,
                                type = CustomButtonType.submit,
                                fullWidth = true
                        )
                    }
                }

                div(classes = "mt-6") {
                    div(classes = "relative") {
                        div(classes = "absolute inset-0 flex items-center") {
                            div(classes = "w-full border-t border-gray-300") {}
                        }
                        div(classes = "relative flex justify-center text-sm") {
                            span(classes = "px-2 bg-gray-50 text-gray-500") { +"Or continue with" }
                        }
                    }
                }

                div(classes = "mt-6") {
                    a(
                            href = "/auth/google",
                            classes =
                                    "w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50"
                    ) {
                        img(
                                classes = "w-5 h-5 mr-2",
                                src =
                                        "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Cpath d='M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z' fill='%234285f4'/%3E%3Cpath d='M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z' fill='%2334a853'/%3E%3Cpath d='M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z' fill='%23fbbc05'/%3E%3Cpath d='M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z' fill='%23ea4335'/%3E%3C/svg%3E",
                                alt = "Google"
                        )
                        span { +"Sign in with Google" }
                    }
                }
            }
        }
    }
}
