import styled from 'styled-components';

export const CardSection = styled.section`
  cursor: pointer;
  width: 300px;
  background-color: white;
  overflow: hidden;
  position: relative;
  display: flex;
  flex-direction: column;
  border-radius: ${(props) => props.theme.border_radius.lv2};
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
`;

export const Image = styled.img`
  border-bottom-left-radius: ${(props) => props.theme.border_radius.lv2};
  border-bottom-right-radius: ${(props) => props.theme.border_radius.lv2};
  width: 100%;
  height: 150px; /* Adjust the height as needed */
  object-fit: cover;
`;

export const TextContainer = styled.div`
  padding: 20px;
  font-size: 20px;
`;

export const ToggleButton = styled.button`
  cursor: pointer;
  background-color: transparent;
  color: ${(props) => (props.scrap ? 'green' : 'black')};
  border: none;
  /* padding-bottom: 15px;
  padding-right: 15px;
  margin-top: auto;
  margin-left: auto; */
  margin-top: auto;
  margin-left: auto;
  margin-bottom: 15px;
  margin-right: 15px;
  font-size: 20px;
  font-weight: bold;

  &:hover {
    color: ${(props) => (props.scrap ? 'black' : 'darked')};
  }
`;

