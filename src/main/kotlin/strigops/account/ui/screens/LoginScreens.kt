package strigops.account.ui.screens

import kotlinx.html.*
import strigops.account.ui.assets.*
import strigops.account.ui.components.*
import strigops.account.ui.layouts.*

fun HTML.loginPage(errorMessage: String? = null) {
    mainLayout(title = "Sign in - StrigoAccount") {
        div(classes = "flex-grow flex items-center justify-center px-4 py-12 h-screen") {
            div(
                    classes =
                            "w-full max-w-md bg-white dark:bg-gray-800 shadow-2xl rounded-3xl p-8 space-y-6 border border-gray-100 dark:border-gray-700 transition-all duration-300 overflow-y-auto"
            ) {
                div(classes = "text-center") {
                    h2(
                            classes =
                                    "text-3xl font-extrabold text-gray-900 dark:text-white tracking-tight"
                    ) { +"Welcome back" }
                    p(classes = "mt-2 text-sm text-gray-500 dark:text-gray-400") {
                        +"Sign in to continue to your developer dashboard."
                    }
                }

                errorMessage?.let {
                    div(classes = "animate-pulse") { Alert(it, type = AlertType.ERROR) }
                }

                form(action = "/auth/login", method = FormMethod.post, classes = "space-y-6") {
                    input(type = InputType.hidden, name = "_csrf") {}

                    div(classes = "space-y-4") {
                        Input(
                                label = "Email or Username",
                                name = "email",
                                inputType = InputType.email,
                                icon = StrigoIcon.EMAIL,
                                placeholder = "Enter your email"
                        )

                        Input(
                                label = "Password",
                                name = "password",
                                inputType = InputType.password,
                                icon = StrigoIcon.PASSWORD,
                                placeholder = "Enter your password"
                        )
                    }

                    div(classes = "flex items-center justify-between") {
                        div(classes = "flex items-center") {
                            input(
                                    type = InputType.checkBox,
                                    name = "remember-me",
                                    classes =
                                            "custom-checkbox h-4 w-4 text-blue-600 border-gray-300 rounded transition duration-150 ease-in-out"
                            ) { id = "remember-me" }
                            label(classes = "ml-2 block text-sm text-gray-700 dark:text-gray-300") {
                                attributes["for"] = "remember-me"
                                +"Remember me for 30 days"
                            }
                        }

                        a(
                                href = "/auth/forgot",
                                classes =
                                        "text-sm font-semibold text-blue-600 hover:text-blue-500 transition-colors"
                        ) { +"Forgot password?" }
                    }

                    Button(
                            text = "Login",
                            variant = ButtonVariant.PRIMARY,
                            type = CustomButtonType.submit,
                            fullWidth = true
                    )
                }

                div(classes = "relative my-6") {
                    div(classes = "absolute inset-0 flex items-center") {
                        div(classes = "w-full border-t border-gray-200 dark:border-gray-700") {}
                    }
                    div(classes = "relative flex justify-center text-sm uppercase") {
                        span(classes = "px-4 bg-white dark:bg-gray-800 text-gray-400 font-medium") {
                            +"OR"
                        }
                    }
                }

                div {
                    a(
                            href = "#",
                            classes =
                                    "w-full flex items-center justify-center gap-3 py-3 px-4 border border-gray-300 dark:border-gray-600 rounded-xl bg-white dark:bg-transparent text-sm font-semibold text-gray-700 dark:text-gray-200 hover:bg-gray-50 dark:hover:bg-gray-700 transition-all duration-200 group"
                    ) {
                        img(
                                classes = "w-5 h-5 group-hover:scale-110 transition-transform",
                                src =
                                        "https://www.gstatic.com/images/branding/product/1x/gsa_512dp.png",
                                alt = "Google"
                        )
                        span { +"Continue with Google" }
                    }
                }

                p(classes = "text-center text-sm text-gray-500 dark:text-gray-400") {
                    +"Don't have an account? "
                    a(
                            href = "/auth/register",
                            classes =
                                    "font-bold text-blue-600 hover:text-blue-500 underline-offset-4 hover:underline"
                    ) { +"Sign up for free" }
                }
            }
        }

        // footer(classes = "py-2 text-center text-xs text-gray-400 dark:text-gray-600 w-full") {
        //     div(classes = "flex justify-center gap-6 mb-2") {
        //         a(href = "#") { +"Privacy Policy" }
        //         a(href = "#") { +"Terms of Service" }
        //         a(href = "#") { +"Contact Support" }
        //     }
        //     p { +"© 2024 Architect Serenity. All rights reserved." }
        // }
    }
}
