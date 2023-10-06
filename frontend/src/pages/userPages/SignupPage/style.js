import styled from "styled-components";

export const Main = styled.main`
display: flex;
flex-direction: column;
margin: 0 auto;
width: 60%;
`

export const Header = styled.header`
   margin-bottom :50px ;
`
export const Footer = styled.footer`
text-align:right ;

margin :5px ;

border-top :2px solid ;

padding-top :5px ;

p {
font-size :20px ;
}

`
export const Title = styled.h2`
   font-size :3em ; 
   margin: auto;
   margin-bottom :30px ;
`

export const SigninForm=styled.form`
   display:flex ; 
   flex-direction : column ;
   margin: auto;
   width: 60%;
   & > input + input{
       margin-top :20px;  
     }
`

export const TextWrapper=styled.div`
margin-top :20px;  
display:flex ; 

& > p{
margin-right :10px;  
}

& > a{
margin-right :10px;  
}
`