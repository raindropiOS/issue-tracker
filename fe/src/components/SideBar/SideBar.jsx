import { styled } from 'styled-components';
import DropDown from '../DropDown/DropDown';

const SideBar = () => {
  return (
    <div style={{ flexGrow: 1 }}>
      <SideBarBox>
        <SideBarRow
          config={{
            buttonText: '담당자',
            bodyItems: [{ text: 'chloe' }],
            bodyCheck: true,
          }}
        />
        <SideBarRow config={{ buttonText: '레이블', bodyItems: [] }} />
        <SideBarRow config={{ buttonText: '마일스톤', bodyItems: [] }} />
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
