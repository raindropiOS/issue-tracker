import { styled } from 'styled-components';
import DropDown from '../DropDown/DropDown';

const SideBar = ({
  labels,
  selectedLabelsIds,
  onLabelClick,
  milestones,
  selectedMilestoneId,
  onMilestoneClick,
  users,
  selectedUserIds,
  onUserClick,
}) => {
  const usersConfig = {
    buttonText: '담당자',
    bodyItems: users.map(({ imgUrl, id, nickname }) => {
      return {
        key: id,
        userImage: imgUrl,
        text: nickname,
        handleItemClick: () => onUserClick(id),
        checked: selectedUserIds.includes(id),
      };
    }),
    bodyCheck: true,
    pos: 'center',
  };

  const labelsConfig = {
    buttonText: '레이블',
    bodyItems: labels.map(({ backgroundColor, id, name }) => {
      return {
        key: id,
        labelColor: backgroundColor,
        text: name,
        handleItemClick: () => onLabelClick(id),
        checked: selectedLabelsIds.includes(id),
      };
    }),
    bodyCheck: true,
    pos: 'center',
  };

  const milestonesConfig = {
    buttonText: '마일스톤',
    bodyItems: milestones.map(({ id, name }) => {
      return {
        key: id,
        text: name,
        handleItemClick: () => onMilestoneClick(id),
        checked: selectedMilestoneId === id,
      };
    }),
    bodyCheck: true,
    pos: 'center',
  };
  return (
    <div style={{ flexGrow: 1 }}>
      <SideBarBox>
        <SideBarRow config={usersConfig} />
        <SideBarRow config={labelsConfig} />
        <SideBarRow config={milestonesConfig} />
      </SideBarBox>
    </div>
  );
};

export default SideBar;

const SideBarBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1px;

  background: ${({ theme }) => theme.color.neutralBorder};
  border: 1px solid ${({ theme }) => theme.color.neutralBackgroundBold};
  border-radius: 16px;
`;

const SideBarRow = styled(DropDown)`
  padding: 32px;
  display: flex;
  justify-content: space-between;

  background-color: ${({ theme }) => theme.color.neutralBackgroundStrong};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
  font-size: ${({ theme }) => theme.fontSize.M.size};
  color: ${({ theme }) => theme.color.neutralTextWeak};

  &:first-child {
    border-radius: 16px 16px 0 0;
  }

  &:last-child {
    border-radius: 0 0 16px 16px;
  }
`;
