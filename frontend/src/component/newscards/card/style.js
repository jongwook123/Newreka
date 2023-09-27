import styled from "styled-components";

export const CardSection = styled.section`
  cursor: pointer;
  width: 300px;
  background-color: white;
  overflow-x: hidden;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border-radius: ${(props) => props.theme.border_radius.lv2};
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  & > img {
    border-bottom-left-radius:  ${(props) => props.theme.border_radius.lv2};
    border-bottom-right-radius:  ${(props) => props.theme.border_radius.lv2};
    width: 100%;
    height: 50%;
    object-fit: cover; 
  }
`;
export const TextContainer = styled.div`
  padding: 30px;
  & > h2 {
    font-size: ${(props) => props.theme.font_size.lv4};
    font-weight: 500;
  }
`;