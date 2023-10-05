import styled from "styled-components";
export const Main = styled.main`
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    width: 60%;
    height: 2000px;
`

export const Header = styled.header`
    border-bottom: 2px solid;
`

export const BodySection = styled.section`
    display: flex;
    flex-direction: column;
    align-items:center;
`


export const Body = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin: 20px;
    padding: 30px;
    width: 95%;
    height : 100%;
    background-color : #F5ECE5;
    border-radius: 20px;
    box-shadow: 0 5px 5px #E3C3A6; 
    & h2 {
        font-weight: bold;
        font-size: 30px;
        padding-top: 20px;
        padding-left: 20px;
        align-self: flex-start;
    }
    & p {
        font-size: 20px;
    }
`
