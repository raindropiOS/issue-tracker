import styled from 'styled-components';
import { ReactComponent as CheckBoxInitialIcon } from '../../../assets/checkBoxInitial.svg';

export const CheckBoxInitial = ({ handleCheckboxChange }) => {
  return (
    <CheckBoxInitialBox onClick={handleCheckboxChange}>
      <CheckBoxInitialIcon />
    </CheckBoxInitialBox>
  );
};

const CheckBoxInitialBox = styled.button`
  cursor: pointer;
`;
