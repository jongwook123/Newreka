import styled from "styled-components";

export const CardSection = styled.section`
    cursor: pointer;
    width: 90%;
    background-color: white;
    display: grid;
    grid-template-columns: 1fr 5fr;
    border-radius: ${props => props.theme.border_radius.lv2};
    font-size: ${props => props.theme.font_size.lv4};
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
    align-items: center;
    
    & > img {
        width: 100%;
        height: 80px;
        border-top-left-radius: ${props => props.theme.border_radius.lv2};
        border-bottom-left-radius: ${props => props.theme.border_radius.lv2};
    }
`;
export const TextContainer = styled.div`
margin: 10px;
    & > h2 {
        font-size: ${props => props.theme.font_size.lv4};
        font-weight: 300
    }
`