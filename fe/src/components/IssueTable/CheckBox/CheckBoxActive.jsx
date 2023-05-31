import styled from 'styled-components';
import { ReactComponent as CheckBoxActiveIcon } from '../../../assets/checkBoxActive.svg';

export const CheckBoxActive = ({ handleCheckboxChange }) => {
  return (
    <CheckBoxActiveBox onClick={handleCheckboxChange}>
      <CheckBoxActiveIcon />
    </CheckBoxActiveBox>
  );
};

const CheckBoxActiveBox = styled.button`
  cursor: pointer;
`;
