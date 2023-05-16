import styled from 'styled-components';
import chevronDown from '../../../../assets/chevronDown.svg';

const TableFilterButtons = () => {
  const filterTypes = ['담당자', '레이블', '마일스톤', '작성자'];

  return (
    <TableFilterButtonsBox>
      {filterTypes.map((type) => (
        <TableFilterButton key={type}>
          {type}
          <img src={chevronDown} alt="chevronDown" />
        </TableFilterButton>
      ))}
    </TableFilterButtonsBox>
  );
};

export default TableFilterButtons;

const TableFilterButtonsBox = styled.div`
  display: flex;

  gap: 36px;
  color: #6e7191;
`;

const TableFilterButton = styled.div`
  display: flex;

  font-weight: 700;
  font-size: 16px;
  gap: 8px;
  cursor: pointer;
`;
