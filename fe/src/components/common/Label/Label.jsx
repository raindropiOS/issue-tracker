import styled, { css } from 'styled-components';

export const Label = ({ name, ...colors }) => {
  return (
    <LableBox name={name} {...colors}>
      {name}
    </LableBox>
  );
};

const LableBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 24px;
  font-weight: 500;
  font-size: 12px;
  color: ${(p) => p.text_color};
  background-color: ${(p) => p.background_color};
  border-radius: 50px;

  ${({ name }) => {
    const nameLength = name.length;
    const minWidth = 32;

    if (nameLength <= 4) {
      return css`
        width: ${minWidth}px;
        padding-left: 16px;
        padding-right: 16px;
      `;
    }
    const dynamicWidth = nameLength * 8 + 16;
    return css`
      width: ${dynamicWidth}px;
      padding-left: 16px;
      padding-right: 16px;
    `;
  }}
`;
