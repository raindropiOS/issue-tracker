import { styled } from 'styled-components';

const DropDownHeader = ({ headerText }) => {
  return <DropDownBox>{headerText}</DropDownBox>;
};

export default DropDownHeader;

const DropDownBox = styled.div`
  padding: 8px 16px;
  background: #f7f7fc;
  border-radius: 16px 16px 0px 0px;
  font-weight: 500;
  font-size: 12px;
  color: #14142b;
`;
