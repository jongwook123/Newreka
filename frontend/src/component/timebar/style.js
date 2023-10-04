import styled, { css } from "styled-components";

export const TimeBarContainer = styled.div`
    display: flex;
    justify-content: space-between;
    width: 100%;
    padding: 20px;
    background-color: #f8f9fa;
`;

const selectedStyles = css`
    background-color : #dee2e6; 
    color:white;
    font-weight:bold; 
    transform : scale(1.05);
`;

export const TimeSlot = styled.div`
    cursor: pointer;
    padding: 5px;
    margin-right: 5px;
    border-radius: 5px;
    transition-duration: 0.3s;

   ${props => props.selected && selectedStyles}
   
   &:hover {
        ${selectedStyles}
     }
`;
