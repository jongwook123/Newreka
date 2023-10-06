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
    text-align: right;
    width: 20%;
`

export const StyledHr = styled.hr`
  border: none;
  border-top: 2px solid black;
`;

export const Switch = styled.label`
  position: relative;
  display: inline-block;
  width: 52px;
  height: 20px;

  & input {
    opacity: 0;
    width: 0;
    height: 0;

    &:checked + span {
      background-color: #D2B48C;

      &::before {
        transform: translateX(20px); /* 움직임 거리 변경 */
      }
    }

    &:focus + span {
      box-shadow: 0 0 1px #D2B48C;
    }

}

& span.slider {
   position:absolute;
   cursor:pointer; 
   top :0; 
   left :0; 
   right :0; 
   bottom :0; 
   background-color:#ccc; 
   transition:.4s;

&::before{
position:absolute ;
content:"";
height :16px ; /* 동그라미 크기 변경 */  
width :16px ; /* 동그라미 크기 변경 */  
left :2px ; /* 동그라미 초기 위치 변경 */  
bottom :2px ; /* 동그라미 초기 위치 변경 */  
background-color:white ;
transition:.4s ;

}
&.round{
border-radius :34px
}

&.round:before{
border-radius :50%
}
}
`;

export const SwitchWrapper = styled.div`
display:flex;
align-items:center;

span{
margin-right:15px ;
font-size: 20px;
}
`;