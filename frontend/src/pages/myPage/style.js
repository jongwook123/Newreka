import styled from "styled-components";

export const Main = styled.main`
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    width: 60%;
    height: 1500px;
`

export const Header = styled.header`
    border-bottom: 2px solid;
`

export const BodySection = styled.section`
    display: flex;
    flex-direction: column;
    align-items:center;
`


export const Body = styled.body`
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin: 10px;
    padding: 30px;
    width: 95%;
    height : 100%;
    background-color : #F5ECE5;
    border-radius: 20px;
    & h2 {
        font-weight: bold;
        font-size: 30px;
        padding-top: 20px;
        padding-left: 20px;
    }
    & p {
        font-size: 20px;
    }
`

export const KeywordSection = styled.section`
    display: flex;
    justify-content: center;
`