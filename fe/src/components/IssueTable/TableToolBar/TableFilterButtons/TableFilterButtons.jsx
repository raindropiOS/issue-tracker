import styled from 'styled-components';
import chevronDown from '../../../../assets/chevronDown.svg';
import DropDown from '../../../DropDown/DropDown';

const TableFilterButtons = () => {
  const assigneeDropDownConfig = {
    buttonText: '담당자',
    buttonImage: chevronDown,
    headerText: '담당자 필터',
    bodyItems: [
      { text: '담당자가 없는 이슈', checked: false },
      { img: chevronDown, text: 'chloe', checked: false },
      { img: chevronDown, text: 'head', checked: false },
      { img: chevronDown, text: 'sam', checked: false },
      { img: chevronDown, text: 'zello', checked: false },
    ],
    bodyCheck: true,
    posright: true,
    marginTop: '10px',
  };

  const labelDropDownConfig = {
    buttonText: '레이블',
    buttonImage: chevronDown,
    headerText: '레이블 필터',
    bodyItems: [
      { text: '레이블이 없는 이슈', checked: false },
      { img: chevronDown, text: 'documentation', checked: false },
      { img: chevronDown, text: 'bug', checked: false },
    ],
    bodyCheck: true,
    posright: true,
    marginTop: '10px',
  };

  const milestoneDropDownConfig = {
    buttonText: '마일스톤',
    buttonImage: chevronDown,
    headerText: '마일스톤 필터',
    bodyItems: [
      { text: '마일스톤이 없는 이슈', checked: false },
      { text: '그룹프로젝트: 이슈트레커', checked: false },
    ],
    bodyCheck: true,
    posright: true,
    marginTop: '10px',
  };

  const authorDropDownConfig = {
    buttonText: '작성자',
    buttonImage: chevronDown,
    headerText: '작성자 필터',
    bodyItems: [
      { img: chevronDown, text: 'chloe', checked: false },
      { img: chevronDown, text: 'head', checked: false },
      { img: chevronDown, text: 'sam', checked: false },
      { img: chevronDown, text: 'zello', checked: false },
    ],
    bodyCheck: true,
    posright: true,
    marginTop: '10px',
  };

  return (
    <TableFilterButtonsBox>
      <DropDown config={assigneeDropDownConfig} />
      <DropDown config={labelDropDownConfig} />
      <DropDown config={milestoneDropDownConfig} />
      <DropDown config={authorDropDownConfig} />
    </TableFilterButtonsBox>
  );
};

export default TableFilterButtons;

const TableFilterButtonsBox = styled.div`
  display: flex;
  gap: 36px;

  color: ${({ theme }) => theme.color.neutralTextWeak};
`;
