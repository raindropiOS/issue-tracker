import { styled } from 'styled-components';
import checkOffCircle from '../../../../assets/checkOffCircle.svg';

const DropDownBody = ({ bodyItems, bodyCheck }) => {
  // TODO: div 이미지로 수정하기
  const itemList = bodyItems.map(({ img, text }) => {
    return (
      <li key={text}>
        <IconTextBox>
          {img && <img src={img} alt="img" />}
          <span>{text}</span>
        </IconTextBox>

        {bodyCheck && <img src={checkOffCircle} alt="check off circle" />}
      </li>
    );
  });

  return <DropDownBodyList>{itemList}</DropDownBodyList>;
};

export default DropDownBody;

const DropDownBodyList = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 1px;

  font-weight: ${({ theme }) => theme.fontWeight.regular};
  font-size: ${({ theme }) => theme.fontSize.M.size};
  line-height: ${({ theme }) => theme.fontSize.M.lineHeight};
  border-radius: 0px 0px 16px 16px;
  background: ${({ theme }) => theme.color.neutralBorder};

  li {
    display: flex;
    justify-content: space-between;
    padding: 8px 16px;
    background: ${({ theme }) => theme.color.neutralBackgroundStrong};
    cursor: pointer;
  }

  li:last-child {
    border-radius: 0px 0px 16px 16px;
  }
`;

const IconTextBox = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
`;
