import styled, { css } from "styled-components";

export const TimeBarContainer = styled.div`
    display: flex;
    justify-content: space-between;
    width: 100%;
    padding: 10px;
    background-color: #f8f9fa;
    border-radius : 15px;
`;

const selectedStyles = css`
    background-color : #6c757d; 
    color:white;
    font-weight:bold; 
`;

export const TimeSlot = styled.div`
    cursor: pointer;
    padding: 10px 15px; 
    margin-right : 10px;
    border-radius : 15px;
    font-size : 16px;
    font-weight:bold; 
    color : #343a40;
    transition : all 0.3s ease-in-out; 

    ${props => props.selected && selectedStyles}

    &:hover {
        ${selectedStyles}
        transform : scale(1.05); 
     }
`;
