import styled from "styled-components";


export const EachQuiz = styled.div`
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: white;
  transition: background-color 0.3s ease;

  h2 {
    font-size: 1.2rem;
    margin-bottom: 10px;
  }
`;

export const Option = styled.label`
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 93%;
  margin: auto;
  margin-top: 5px;
  background-color: ${(props) => (props.isSelected ? "#EDDCCF" : "white")};
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #F5ECE5;
  }


  input[type="radio"] {
    position: absolute;
    opacity: 0;
  }


  .radio-button {
    width: 20px;
    height: 20px;
    border: 2px solid #ccc;
    border-radius: 50%;
    background-color: white;
    transition: background-color 0.3s ease;


    input[type="radio"]:checked + .radio-button {
      background-color: gray;
    }
  }
`;

export const Button = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
  margin-right: 20px;
`;


export const SubmitButton = styled.button`
  padding: 10px 20px;
  background-color: #EDDCCF; 
  color: black;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #F5ECE5; 
  }
  & p {
    font-size: 15px;
  }
`;



