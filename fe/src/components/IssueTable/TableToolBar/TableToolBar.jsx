import styled from 'styled-components';

const TableToolBar = ({ children }) => {
  return <TableToolBarBox>{children}</TableToolBarBox>;
};

export default TableToolBar;

const TableToolBarBox = styled.div`
  display: flex;
  align-items: center;

  gap: 33px;
  padding: 24px;
  width: 1280px;
  height: 64px;
  background: #f7f7fc;
  border-radius: 16px 16px 0px 0px;
  border: 1px solid #d9dbe9;
  font-size: 16px;
`;
