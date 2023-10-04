import styled from "styled-components";
import IROnly from "styles/IROnly";

export const FieldSet = styled.fieldset`
    width: 100%;
    position: relative;
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
    & > legend {
        ${IROnly}
    }

    &:focus-within > label {
        top: 0;
        left: 10px;
        color: ${(props) => props.color === 'orange' ? `${props.theme.colors.theme.orange_dark}` : props.color === 'green' ? `${props.theme.colors.theme.green_dark}` : `${props.theme.colors.theme.blue_dark}`};
        font-size: ${props => props.theme.font_size.lv3};
    }
`

export const Input = styled.input`
    width: 100%;
    display: block;
    height: 48px;
    padding: 0 15px;
    margin-top: 10px;
    border: 0;
    position: relative;
    border: 2px solid ${props => props.isFill ? props.color === 'orange' ? `${props.theme.colors.theme.orange}` : props.color === 'green' ? `${props.theme.colors.theme.green}` : `${props.theme.colors.theme.blue}` : "0"};
    border-bottom: 2px solid ${props => props.isFill ? props.color === 'orange' ? `${props.theme.colors.theme.orange}` : props.color === 'green' ? `${props.theme.colors.theme.green}` : `${props.theme.colors.theme.blue}` : props.theme.colors.border.light_gray};
    font-size: ${props => props.theme.font_size.lv4};
    border-radius: ${props => props.isFill ? props.theme.border_radius.lv2 : "0"};
    border-radius:10px;
    &:focus {
        outline: 0;
        border: 2px solid ${(props) => props.color === 'orange' ? `${props.theme.colors.theme.orange_dark}` : props.color === 'green' ? `${props.theme.colors.theme.green_dark}` : `${props.theme.colors.theme.blue_dark}`};    
        border-radius:10px;
    }

    &::placeholder {
        color: ${props => props.theme.colors.font.light_gray2};
    }

   
`