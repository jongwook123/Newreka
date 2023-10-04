import styled from "styled-components";
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';

export const CustomTabs = styled(Tabs)`
    width: 95%;
    padding: 20px;

`;

export const CustomTabList = styled(TabList)`
    display: flex;
    cursor: pointer;
    text-align: center;
    border-bottom: 1px solid black;
`;

export const CustomTab = styled(Tab)`
    padding: 8px 16px;
    background-color: ${(props) => (props.selected ? '#F7DAC4' : '#F5ECE5')};
    font-size:18px;
    font-weight: bold;
    &:focus {
        outline: none; 
    }
    
    flex-basis: calc(100% / 5);
`;

export const CustomTabPanel = styled(TabPanel)`
   display:${(props)=>(props.selected ? 'block' : 'none')};
   display: flex;
   justify-content: center;
   align-items: center;
   flex-direction: column;
   & > * {
       margin-top: 20px;
   }
`;

export const CardList = styled.ul`
    width: 95%;
    display: grid;
    box-sizing: border-box;
    grid-template-columns : repeat(3 , minmax(0 , auto));
	grid-gap :30px ;
    
    `

export const CardItem = styled.li`
    display: flex;
    justify-content: center;
    width:100%;

        &:hover {
            ${props => props.memberTypeId === 1 && 
            `
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
                transform: scale(1.05);
                cursor: pointer;
                
            `}
        }
`
export const QuizSection = styled.section`
  border-radius: ${(props) => props.theme.border_radius.lv2};
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  background-color: white;
  padding: 30px;
  max-height: 500px;
  overflow-y:auto;
  height: 60%;
  width: 90%;
`;