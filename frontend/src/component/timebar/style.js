import styled from "styled-components";

export const TimeBarContainer = styled.div`
    display: flex;
    justify-content: space-between;
    width: 100%;
    padding: 20px;
    background-color: #f8f9fa; // Light grey background
`;

export const TimeSlot = styled.div`
    cursor: pointer;
    padding: 5px;
    margin-right: 5px; // Add some spacing between the time slots
    border-radius: 5px; // Round the corners for a softer look
    transition-duration: 0.3s; // Smooth hover transition

   /* Change color based on selected state */
   background-color : ${props => props.selected ? '#dee2e6' : 'transparent'};
   color : ${props => props.selected ? 'white' : 'black'};
   
   &:hover {
        background-color : #dee2e6; 
        color:white;
        font-weight:bold; 
        transform : scale(1.05); 
     }
`;
