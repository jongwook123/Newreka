import styled from "styled-components";

export const EachQuiz = styled.div`
    margin: 20px;
`;

export const Option = styled.p`
    cursor: pointer;
    background-color: ${props => props.isSelected ? 'lightgray' : 'white'};
`;
