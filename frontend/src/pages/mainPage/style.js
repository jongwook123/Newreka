import styled from "styled-components";

export const Main = styled.main`
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    width: 60%;
    
`

export const Header = styled.header`

`

export const Body1 = styled.body`
    display: flex;
    justify-content: center;
    border-top: 2px solid;
    margin-top: 10px;
    h2 {
            display: none;
        }
`

export const Body2 = styled.body`
    display: flex;
    justify-content: center;
    h2 {
            display: none;
        }
`

export const Footer = styled.footer`
    text-align: right;
    margin: 5px;
    border-top: 2px solid;
    padding-top: 5px;
`