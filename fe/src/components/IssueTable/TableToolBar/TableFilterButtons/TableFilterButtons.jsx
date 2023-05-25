import { useContext } from 'react';
import styled from 'styled-components';
import {
  ASSIGNEE,
  AUTHOR,
  ISSUE_TABLE_FILTER_OPTIONS,
  LABELS,
  MILESTONE,
  NONE,
} from '../../../../constants';
import { setFilterOption } from '../../../../context/MainPage/MainPageActions';
import {
  MainPageContext,
  MainPageDispatchContext,
} from '../../../../context/MainPage/MainPageContext';
import DropDown from '../../../DropDown/DropDown';

const convertOptionItems = (
  optionItems,
  filterOptionState,
  dispatch,
  action,
  optionType,
) => {
  return optionItems.map((item) => {
    const isChecked = filterOptionState === null
      ? false
      : filterOptionState.includes(item.name);

    const dropDownBodyItemConfig = {
      text: item.nickname || item.name,
      checked: isChecked,
      handleItemClick: () => {
        dispatch(action(optionType, item.id));
      },
    };

    if (item.backgroundColor) dropDownBodyItemConfig.img = item.backgroundColor;
    if (item.imgUrl) dropDownBodyItemConfig.img = item.imgUrl;
    return dropDownBodyItemConfig;
  });
};

const TableFilterButtons = () => {
  const {
    allUsers, allLabels, allMilestones, filterOptions,
  } = useContext(MainPageContext);
  const dispatch = useContext(MainPageDispatchContext);

  const filterDropDownConfigList = ISSUE_TABLE_FILTER_OPTIONS.map(
    (filterOption) => {
      const targetFilterOption = filterOptions[filterOption.KEY];
      const isChecked = targetFilterOption === null
        ? false
        : filterOptions[filterOption.KEY].includes(NONE);

      let bodyItems = filterOption.KO !== AUTHOR.KO
        ? [
          {
            text: `${filterOption.KO} 없는 이슈`,
            checked: isChecked,
            handleItemClick: () => {
              dispatch(setFilterOption(filterOption.KEY, NONE));
            },
          },
        ]
        : [];

      if (filterOption.KO === ASSIGNEE.KO || filterOption.KO === AUTHOR.KO) {
        bodyItems = bodyItems.concat(
          convertOptionItems(
            allUsers,
            targetFilterOption,
            dispatch,
            setFilterOption,
            filterOption.KEY,
          ),
        );
      } else if (filterOption.KO === LABELS.KO) {
        bodyItems = bodyItems.concat(
          convertOptionItems(
            allLabels,
            targetFilterOption,
            dispatch,
            setFilterOption,
            filterOption.KEY,
          ),
        );
      } else if (filterOption.KO === MILESTONE.KO) {
        bodyItems = bodyItems.concat(
          convertOptionItems(
            allMilestones,
            targetFilterOption,
            dispatch,
            setFilterOption,
            filterOption.KEY,
          ),
        );
      }

      return {
        buttonText: filterOption.KO,
        headerText: `${filterOption.KO} 필터`,
        bodyItems,
        bodyCheck: true,
        posright: true,
        marginTop: '10px',
      };
    },
  );

  const filterDropDownList = filterDropDownConfigList.map((config) => (
    <DropDown key={config.buttonText} config={config} />
  ));

  return <TableFilterButtonsBox>{filterDropDownList}</TableFilterButtonsBox>;
};

export default TableFilterButtons;

const TableFilterButtonsBox = styled.div`
  display: flex;
  gap: 36px;

  color: ${({ theme }) => theme.color.neutralTextWeak};
`;
