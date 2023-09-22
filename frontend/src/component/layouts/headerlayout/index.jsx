import Header from "component/header"
import { Outlet } from "react-router"



export default function HeaderLayout() {
    return (
        <>
            <Header />
            <Outlet />
        </>
    )
}
