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

  color: #4e4b66;
  font-weight: 400;
  font-size: 16px;
  line-height: 28px;
  border-radius: 0px 0px 16px 16px;
  background: #d9dbe9;

  li {
    display: flex;
    justify-content: space-between;
    padding: 8px 16px;
    background: #fefefe;
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
