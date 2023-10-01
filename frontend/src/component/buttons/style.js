import styled from "styled-components";

export const Button = styled.button`
    width: 100%;
    height: 48px;
    border: 0;
    transition: all 0.2s;
    background-color: rgb(245, 236, 229);
    border-radius: ${props => props.theme.border_radius.lv2};
    color: black;
    font-size: ${props => props.theme.font_size.lv4};
    margin-top : 10px;
    margin-bottom : 40px;
    &:hover {
        background-color: ${props => props.color === 'orange' ? `${props.theme.colors.theme.orange_dark}` : props.color === 'green' ? `${props.theme.colors.theme.green_dark}` : `${props.theme.colors.theme.blue_dark}`};
    }
`