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
    font-size:16px;
    font-weight: bold;
    &:focus {
        outline: none; 
    }
    
    flex-basis: calc(100% / 7);
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
