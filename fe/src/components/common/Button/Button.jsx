import styled from 'styled-components';

export const Button = ({
  children,
  buttonStatus,
  handleButton,
  buttonText,
  count,
  gap,
  fontWeight,
  fontSize,
  color,
}) => {
  return (
    <ButtonBox
      type="button"
      onClick={handleButton}
      $buttonstatus={buttonStatus}
      gap={gap}
      fontSize={fontSize}
      fontWeight={fontWeight}
      color={color}
    >
      {children}
      {buttonText}
      {count >= 0 && `(${count})`}
    </ButtonBox>
  );
};

const ButtonBox = styled.button`
  display: flex;
  align-items: center;
  ${({ gap }) => `gap: ${gap || '10px'}`};

  ${({ theme, fontWeight }) => `font-weight: ${theme.fontWeight[fontWeight] || theme.fontWeight.regular}`};
  ${({ theme, fontSize }) => `font-size: ${theme.fontSize[fontSize]?.size || theme.fontSize.M.size}`};
  ${({ theme, color }) => `color: ${theme.color[color] || theme.color.neutralText}`};
  cursor: pointer;
`;
