package strigops.account.ui.layouts

import kotlinx.html.*
import strigops.account.ui.components.*

fun HTML.mainLayout(title: String, content: FlowContent.() -> Unit) {
    head {
        meta { charset = "UTF-8" }
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
        title { +title }

        link(
                rel = "stylesheet",
                href = "https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
        )
        link(rel = "preconnect", href = "https://fonts.googleapis.com")
        link(rel = "preconnect", href = "https://fonts.gstatic.com")
        link(
                href =
                        "https://fonts.googleapis.com/css2?family=Public+Sans:wght@400;500;600;700;800;900&display=swap",
                rel = "stylesheet"
        )

        script(src = "https://unpkg.com/lucide@latest") {}

        style {
            unsafe {
                +"""
                body { 
                    font-family: 'Public Sans', sans-serif; 
                    transition: background-color 0.3s ease;
                }

                .bg-text-container {
                    position: fixed;
                    top: 0;
                    left: 0;
                    z-index: 0;
                    pointer-events: none;
                    width: 100%;
                    height: 100%;
                    overflow: hidden;
                }

                .bg-text-item {
                    position: absolute;
                    font-size: 15vw;
                    font-weight: 900;
                    line-height: 0.8;
                    letter-spacing: -0.05em;
                    color: rgba(0, 0, 0, 0.04);
                    margin: 0;
                    padding: 0;
                    white-space: nowrap;
                }

                .bg-text-item:nth-child(1) {
                    top: 25%;
                    left: 10%;
                }

                .bg-text-item:nth-child(2) {
                    bottom: 25%;
                    right: 10%;
                }

                .lucide {
                    transition: transform 0.2s cubic-bezier(0.4, 0, 0.2, 1), stroke-width 0.2s ease;
                }

                button:hover .lucide, a:hover .lucide {
                    transform: scale(1.1) rotate(5deg);
                }

                .dark .bg-text-item {
                    color: rgba(255, 255, 255, 0.05);
                }
                
                .dark-mode-transition {
                    transition: background-color 0.5s ease, color 0.5s ease;
                }
                """
            }
        }
    }
    body(
            classes =
                    "bg-white dark:bg-gray-900 text-gray-900 dark:text-gray-100 antialiased dark-mode-transition"
    ) {
        div(classes = "bg-text-container") {
            div(classes = "bg-text-item") { +"Strigops" }
            div(classes = "bg-text-item") { +"Strigops" }
        }

        div(classes = "relative z-10 min-h-screen flex flex-col") { content() }

        script {
            unsafe {
                +"""
                lucide.createIcons();

                document.querySelectorAll('button, input, a').forEach(el => {
                    el.addEventListener('mouseenter', () => {
                        const icon = el.querySelector('.lucide');
                        if(icon) icon.setAttribute('stroke-width', '2.5');
                    });
                    el.addEventListener('mouseleave', () => {
                        const icon = el.querySelector('.lucide');
                        if(icon) icon.setAttribute('stroke-width', '2');
                    });
                });

                document.addEventListener('DOMContentLoaded', () => {
                    const firstInput = document.querySelector('input');
                    if (firstInput) firstInput.focus();
                });
                """
            }
        }
    }
}
