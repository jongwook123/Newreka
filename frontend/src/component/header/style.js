import styled from "styled-components";


export const Main = styled.main`
    
`
export const Date = styled.header`
    text-align: right;
    margin: 5px;
    font-weight: bold;
    font-size: 21px;

`

export const Title = styled.h1`
    text-align: center;
    font-size: 60px;
    width: 60%;
    font-weight: bold;
    
`

export const Title_div = styled.div`
    display: flex;
    flex-direction : row;
    justify-content: space-around;
    align-items: center;
    border-top: 2px solid;
    border-bottom: 2px solid;

    padding-top: 2%;
    padding-bottom: 2%;
    
`

export const Sub_menu = styled.span`
    text-align: right; /* 오른쪽 정렬 */
    font-size: 25px;
    width: 20%;
    padding-right: 30px;
    font-weight: 500;
`
export const Dummy = styled.span`
    text-align: right; /* 오른쪽 정렬 */
    width: 20%;
`

export const StyledHr = styled.hr`
  border: none;
  border-top: 2px solid black;
`;