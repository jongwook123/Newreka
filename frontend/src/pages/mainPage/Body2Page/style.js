import styled from "styled-components";

export const Main = styled.main`
    margin: 20px auto;
    width: 95%;
    min-height : 800px;
    background-color : #F5ECE5;
    border-radius: 20px;
`

export const Title = styled.h1`
   padding-top: 50px; 
   font-size : 40px; 
   color:#333333 ; 
`

export const Menu = styled.section`
   display:flex ;
   padding-top :30px ;  
`

export const MenuList = styled.span`
   font-size :30px ;
   cursor:pointer ;
   color: ${props => props.selected ? '#FF6347' : '#333333'}; 

   &:hover {
      color:#FF6347 ;  
      transition:.3s ease-in-out ;
     }
`


export const MenuBody = styled.div`
    display:flex ;
     width:90% ;
     height:auto ;

     margin-top :30px ;
`


