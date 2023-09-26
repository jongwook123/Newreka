import { ThemeProvider } from "styled-components"
import { BrowserRouter, Routes, Route } from "react-router-dom"

import { theme } from "styles/Theme"
import { GlobalStyle } from "styles/GlobalStyle"

import MainPage from "pages/mainPage"
import MyPage from "pages/myPage"
import LoginPage from "pages/userPages/LoginPage/LoginPage"
import HeaderLayout from "component/layouts/headerlayout"

export default function App() {
    return (
        <ThemeProvider theme={theme}>
            <GlobalStyle />
            <BrowserRouter basename={process.env.PUBLIC_URL}>
                <header>
                    <meta
                        http-equiv="Content-Security-Policy"
                        content="upgrade-insecure-requests"
                    />
                </header>
                <Routes>
                    <Route path="/login" element={<LoginPage />}></Route>
                    {/* 헤더 */}

                    <Route element={<HeaderLayout />} />
                    <Route path="/" element={<MainPage />} />
                    <Route path="/mypage" element={<MyPage />} />

                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    )
}