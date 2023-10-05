import styled, { keyframes } from 'styled-components';

export const Main = styled.main`
    display: grid;
    grid-template-columns: 7fr 3fr;
    justify-content: center;
    
    width:75%;
`

const scroll = keyframes`
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-100%);
  }
`;

export const KeywordListContainer = styled.div`
    display: flex;
    flex-direction: column; 
    overflow-y : auto;
    white-space: nowrap; 
    height: 80%;
    width:100px;
`;

export const Keyword = styled.span`
      display:block;
      height :20px;
      margin-bottom :10px; 
      color:black; 
      font-weight:bold;

      animation-name:${scroll};
      animation-duration :10s;
      animation-timing-function :linear;
      animation-iteration-count :infinite;
      animation-direction :alternate;

`;
