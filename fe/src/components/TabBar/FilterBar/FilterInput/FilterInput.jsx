import { styled } from 'styled-components';
import { useEffect, useState, useContext } from 'react';
import { ReactComponent as Search } from '../../../../assets/search.svg';
import { generateQueryString } from '../../../../utils/generateQueryString';
import { MainPageContext } from '../../../../context/MainPage/MainPageContext';

const getModifiedQueryString = (queryString) => {
  const queryStringParts = queryString.split('&');
  const firstPart = queryStringParts[0];

  let modifiedFirstPart = firstPart;
  if (firstPart.endsWith('true')) {
    modifiedFirstPart = 'is:open';
  } else if (firstPart.endsWith('false')) {
    modifiedFirstPart = 'is:closed';
  }

  queryStringParts[0] = modifiedFirstPart;
  return queryStringParts.join(' ');
};

const FilterInput = () => {
  const { filterOptions } = useContext(MainPageContext);
  const [inputValue, setInputValue] = useState('');

  // ?: useEffect를 제대로 사용한건지?
  useEffect(() => {
    const modifiedQueryString = getModifiedQueryString(
      generateQueryString(filterOptions),
    );
    setInputValue(modifiedQueryString);
  }, [filterOptions]);

  return (
    <FilterInputBox>
      <SearchImage />
      <input
        placeholder="Search all issues"
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
      />
    </FilterInputBox>
  );
};

export default FilterInput;

const SearchImage = styled(Search)`
  stroke: ${({ theme }) => theme.color.neutralTextWeak};
`;

const FilterInputBox = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  flex-grow: 1;

  background: ${({ theme }) => theme.color.neutralBackgroundBold};
  border-radius: 0px 11px 11px 0px;
  padding: 6px 24px;

  input {
    font-weight: ${({ theme }) => theme.fontWeight.regular};
    width: 400px;
    border: none;
    font-size: ${({ theme }) => theme.fontSize.M.size};
    line-height: ${({ theme }) => theme.fontSize.M.lineHeight};
  }
`;
