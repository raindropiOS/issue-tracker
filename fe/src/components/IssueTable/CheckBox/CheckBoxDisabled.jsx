import styled from 'styled-components';
import { ReactComponent as CheckBoxDisabledIcon } from '../../../assets/checkBoxDisabled.svg';

export const CheckBoxDisabled = ({ handleCheckboxChange }) => {
  return (
    <CheckBoxDisabledBox onClick={handleCheckboxChange}>
      <CheckBoxDisabledIcon />
    </CheckBoxDisabledBox>
  );
};

const CheckBoxDisabledBox = styled.button`
  cursor: pointer;
`;
