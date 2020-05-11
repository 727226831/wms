package com.lkrh.storescontrol;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.lkrh.storescontrol.databinding.ActivityAreaselectBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityBillDetailBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityBillListBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityChartListBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityClaimMaterialBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityConfirmBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityConfirmlist2BindingImpl;
import com.lkrh.storescontrol.databinding.ActivityConfirmlistBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityHelpBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityIndexBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityInspectionlist2BindingImpl;
import com.lkrh.storescontrol.databinding.ActivityInspectionlistBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityLoginBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityLogisticsDistributionBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityManageBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityMaterialBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityOrderChangeBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityPackingInspetionBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityPrintBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityProductionListBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityProductionOrderBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityProductionwarehousingBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityPutListBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityPutdetailBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityQualifiedListBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityReportBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityStockAllocationBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityStockcheckBindingImpl;
import com.lkrh.storescontrol.databinding.ActivitySupplementListBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityTestBindingImpl;
import com.lkrh.storescontrol.databinding.ActivityTestlistBindingImpl;
import com.lkrh.storescontrol.databinding.ItemCkkbBindingImpl;
import com.lkrh.storescontrol.databinding.ItemCodelistBindingImpl;
import com.lkrh.storescontrol.databinding.ItemCompletion1BindingImpl;
import com.lkrh.storescontrol.databinding.ItemCompletion2BindingImpl;
import com.lkrh.storescontrol.databinding.ItemConfirm1BindingImpl;
import com.lkrh.storescontrol.databinding.ItemConfirm2BindingImpl;
import com.lkrh.storescontrol.databinding.ItemDetailListBindingImpl;
import com.lkrh.storescontrol.databinding.ItemDetailsBindingImpl;
import com.lkrh.storescontrol.databinding.ItemDhcjBindingImpl;
import com.lkrh.storescontrol.databinding.ItemDhsjBindingImpl;
import com.lkrh.storescontrol.databinding.ItemDispatchdetailsBindingImpl;
import com.lkrh.storescontrol.databinding.ItemHwydBindingImpl;
import com.lkrh.storescontrol.databinding.ItemInputBindingImpl;
import com.lkrh.storescontrol.databinding.ItemInspectionBindingImpl;
import com.lkrh.storescontrol.databinding.ItemListBindingImpl;
import com.lkrh.storescontrol.databinding.ItemPackingInspetionBindingImpl;
import com.lkrh.storescontrol.databinding.ItemProductionListBindingImpl;
import com.lkrh.storescontrol.databinding.ItemPurchaseBindingImpl;
import com.lkrh.storescontrol.databinding.ItemQualifiedBindingImpl;
import com.lkrh.storescontrol.databinding.ItemStockcheckBindingImpl;
import com.lkrh.storescontrol.databinding.ItemUnqualifiedBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYAREASELECT = 1;

  private static final int LAYOUT_ACTIVITYBILLDETAIL = 2;

  private static final int LAYOUT_ACTIVITYBILLLIST = 3;

  private static final int LAYOUT_ACTIVITYCHARTLIST = 4;

  private static final int LAYOUT_ACTIVITYCLAIMMATERIAL = 5;

  private static final int LAYOUT_ACTIVITYCONFIRM = 6;

  private static final int LAYOUT_ACTIVITYCONFIRMLIST = 7;

  private static final int LAYOUT_ACTIVITYCONFIRMLIST2 = 8;

  private static final int LAYOUT_ACTIVITYHELP = 9;

  private static final int LAYOUT_ACTIVITYINDEX = 10;

  private static final int LAYOUT_ACTIVITYINSPECTIONLIST = 11;

  private static final int LAYOUT_ACTIVITYINSPECTIONLIST2 = 12;

  private static final int LAYOUT_ACTIVITYLOGIN = 13;

  private static final int LAYOUT_ACTIVITYLOGISTICSDISTRIBUTION = 14;

  private static final int LAYOUT_ACTIVITYMANAGE = 15;

  private static final int LAYOUT_ACTIVITYMATERIAL = 16;

  private static final int LAYOUT_ACTIVITYORDERCHANGE = 17;

  private static final int LAYOUT_ACTIVITYPACKINGINSPETION = 18;

  private static final int LAYOUT_ACTIVITYPRINT = 19;

  private static final int LAYOUT_ACTIVITYPRODUCTIONLIST = 20;

  private static final int LAYOUT_ACTIVITYPRODUCTIONORDER = 21;

  private static final int LAYOUT_ACTIVITYPRODUCTIONWAREHOUSING = 22;

  private static final int LAYOUT_ACTIVITYPUTLIST = 23;

  private static final int LAYOUT_ACTIVITYPUTDETAIL = 24;

  private static final int LAYOUT_ACTIVITYQUALIFIEDLIST = 25;

  private static final int LAYOUT_ACTIVITYREPORT = 26;

  private static final int LAYOUT_ACTIVITYSTOCKALLOCATION = 27;

  private static final int LAYOUT_ACTIVITYSTOCKCHECK = 28;

  private static final int LAYOUT_ACTIVITYSUPPLEMENTLIST = 29;

  private static final int LAYOUT_ACTIVITYTEST = 30;

  private static final int LAYOUT_ACTIVITYTESTLIST = 31;

  private static final int LAYOUT_ITEMCKKB = 32;

  private static final int LAYOUT_ITEMCODELIST = 33;

  private static final int LAYOUT_ITEMCOMPLETION1 = 34;

  private static final int LAYOUT_ITEMCOMPLETION2 = 35;

  private static final int LAYOUT_ITEMCONFIRM1 = 36;

  private static final int LAYOUT_ITEMCONFIRM2 = 37;

  private static final int LAYOUT_ITEMDETAILLIST = 38;

  private static final int LAYOUT_ITEMDETAILS = 39;

  private static final int LAYOUT_ITEMDHCJ = 40;

  private static final int LAYOUT_ITEMDHSJ = 41;

  private static final int LAYOUT_ITEMDISPATCHDETAILS = 42;

  private static final int LAYOUT_ITEMHWYD = 43;

  private static final int LAYOUT_ITEMINPUT = 44;

  private static final int LAYOUT_ITEMINSPECTION = 45;

  private static final int LAYOUT_ITEMLIST = 46;

  private static final int LAYOUT_ITEMPACKINGINSPETION = 47;

  private static final int LAYOUT_ITEMPRODUCTIONLIST = 48;

  private static final int LAYOUT_ITEMPURCHASE = 49;

  private static final int LAYOUT_ITEMQUALIFIED = 50;

  private static final int LAYOUT_ITEMSTOCKCHECK = 51;

  private static final int LAYOUT_ITEMUNQUALIFIED = 52;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(52);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_areaselect, LAYOUT_ACTIVITYAREASELECT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_bill_detail, LAYOUT_ACTIVITYBILLDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_bill_list, LAYOUT_ACTIVITYBILLLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_chart_list, LAYOUT_ACTIVITYCHARTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_claim_material, LAYOUT_ACTIVITYCLAIMMATERIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_confirm, LAYOUT_ACTIVITYCONFIRM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_confirmlist, LAYOUT_ACTIVITYCONFIRMLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_confirmlist2, LAYOUT_ACTIVITYCONFIRMLIST2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_help, LAYOUT_ACTIVITYHELP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_index, LAYOUT_ACTIVITYINDEX);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_inspectionlist, LAYOUT_ACTIVITYINSPECTIONLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_inspectionlist2, LAYOUT_ACTIVITYINSPECTIONLIST2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_logistics_distribution, LAYOUT_ACTIVITYLOGISTICSDISTRIBUTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_manage, LAYOUT_ACTIVITYMANAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_material, LAYOUT_ACTIVITYMATERIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_order_change, LAYOUT_ACTIVITYORDERCHANGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_packing_inspetion, LAYOUT_ACTIVITYPACKINGINSPETION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_print, LAYOUT_ACTIVITYPRINT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_production_list, LAYOUT_ACTIVITYPRODUCTIONLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_production_order, LAYOUT_ACTIVITYPRODUCTIONORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_productionwarehousing, LAYOUT_ACTIVITYPRODUCTIONWAREHOUSING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_put_list, LAYOUT_ACTIVITYPUTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_putdetail, LAYOUT_ACTIVITYPUTDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_qualified_list, LAYOUT_ACTIVITYQUALIFIEDLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_report, LAYOUT_ACTIVITYREPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_stock_allocation, LAYOUT_ACTIVITYSTOCKALLOCATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_stockcheck, LAYOUT_ACTIVITYSTOCKCHECK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_supplement_list, LAYOUT_ACTIVITYSUPPLEMENTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_test, LAYOUT_ACTIVITYTEST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.activity_testlist, LAYOUT_ACTIVITYTESTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_ckkb, LAYOUT_ITEMCKKB);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_codelist, LAYOUT_ITEMCODELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_completion1, LAYOUT_ITEMCOMPLETION1);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_completion2, LAYOUT_ITEMCOMPLETION2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_confirm1, LAYOUT_ITEMCONFIRM1);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_confirm2, LAYOUT_ITEMCONFIRM2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_detail_list, LAYOUT_ITEMDETAILLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_details, LAYOUT_ITEMDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_dhcj, LAYOUT_ITEMDHCJ);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_dhsj, LAYOUT_ITEMDHSJ);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_dispatchdetails, LAYOUT_ITEMDISPATCHDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_hwyd, LAYOUT_ITEMHWYD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_input, LAYOUT_ITEMINPUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_inspection, LAYOUT_ITEMINSPECTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_list, LAYOUT_ITEMLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_packing_inspetion, LAYOUT_ITEMPACKINGINSPETION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_production_list, LAYOUT_ITEMPRODUCTIONLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_purchase, LAYOUT_ITEMPURCHASE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_qualified, LAYOUT_ITEMQUALIFIED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_stockcheck, LAYOUT_ITEMSTOCKCHECK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.lkrh.storescontrol.R.layout.item_unqualified, LAYOUT_ITEMUNQUALIFIED);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYAREASELECT: {
        if ("layout/activity_areaselect_0".equals(tag)) {
          return new ActivityAreaselectBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_areaselect is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYBILLDETAIL: {
        if ("layout/activity_bill_detail_0".equals(tag)) {
          return new ActivityBillDetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_bill_detail is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYBILLLIST: {
        if ("layout/activity_bill_list_0".equals(tag)) {
          return new ActivityBillListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_bill_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCHARTLIST: {
        if ("layout/activity_chart_list_0".equals(tag)) {
          return new ActivityChartListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_chart_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCLAIMMATERIAL: {
        if ("layout/activity_claim_material_0".equals(tag)) {
          return new ActivityClaimMaterialBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_claim_material is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCONFIRM: {
        if ("layout/activity_confirm_0".equals(tag)) {
          return new ActivityConfirmBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_confirm is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCONFIRMLIST: {
        if ("layout/activity_confirmlist_0".equals(tag)) {
          return new ActivityConfirmlistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_confirmlist is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCONFIRMLIST2: {
        if ("layout/activity_confirmlist2_0".equals(tag)) {
          return new ActivityConfirmlist2BindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_confirmlist2 is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYHELP: {
        if ("layout/activity_help_0".equals(tag)) {
          return new ActivityHelpBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_help is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYINDEX: {
        if ("layout/activity_index_0".equals(tag)) {
          return new ActivityIndexBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_index is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYINSPECTIONLIST: {
        if ("layout/activity_inspectionlist_0".equals(tag)) {
          return new ActivityInspectionlistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_inspectionlist is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYINSPECTIONLIST2: {
        if ("layout/activity_inspectionlist2_0".equals(tag)) {
          return new ActivityInspectionlist2BindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_inspectionlist2 is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLOGIN: {
        if ("layout/activity_login_0".equals(tag)) {
          return new ActivityLoginBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLOGISTICSDISTRIBUTION: {
        if ("layout/activity_logistics_distribution_0".equals(tag)) {
          return new ActivityLogisticsDistributionBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_logistics_distribution is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMANAGE: {
        if ("layout/activity_manage_0".equals(tag)) {
          return new ActivityManageBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_manage is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMATERIAL: {
        if ("layout/activity_material_0".equals(tag)) {
          return new ActivityMaterialBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_material is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYORDERCHANGE: {
        if ("layout/activity_order_change_0".equals(tag)) {
          return new ActivityOrderChangeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_order_change is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPACKINGINSPETION: {
        if ("layout/activity_packing_inspetion_0".equals(tag)) {
          return new ActivityPackingInspetionBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_packing_inspetion is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPRINT: {
        if ("layout/activity_print_0".equals(tag)) {
          return new ActivityPrintBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_print is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPRODUCTIONLIST: {
        if ("layout/activity_production_list_0".equals(tag)) {
          return new ActivityProductionListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_production_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPRODUCTIONORDER: {
        if ("layout/activity_production_order_0".equals(tag)) {
          return new ActivityProductionOrderBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_production_order is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPRODUCTIONWAREHOUSING: {
        if ("layout/activity_productionwarehousing_0".equals(tag)) {
          return new ActivityProductionwarehousingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_productionwarehousing is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPUTLIST: {
        if ("layout/activity_put_list_0".equals(tag)) {
          return new ActivityPutListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_put_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPUTDETAIL: {
        if ("layout/activity_putdetail_0".equals(tag)) {
          return new ActivityPutdetailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_putdetail is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYQUALIFIEDLIST: {
        if ("layout/activity_qualified_list_0".equals(tag)) {
          return new ActivityQualifiedListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_qualified_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYREPORT: {
        if ("layout/activity_report_0".equals(tag)) {
          return new ActivityReportBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_report is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTOCKALLOCATION: {
        if ("layout/activity_stock_allocation_0".equals(tag)) {
          return new ActivityStockAllocationBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_stock_allocation is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTOCKCHECK: {
        if ("layout/activity_stockcheck_0".equals(tag)) {
          return new ActivityStockcheckBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_stockcheck is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSUPPLEMENTLIST: {
        if ("layout/activity_supplement_list_0".equals(tag)) {
          return new ActivitySupplementListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_supplement_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTEST: {
        if ("layout/activity_test_0".equals(tag)) {
          return new ActivityTestBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_test is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTESTLIST: {
        if ("layout/activity_testlist_0".equals(tag)) {
          return new ActivityTestlistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_testlist is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCKKB: {
        if ("layout/item_ckkb_0".equals(tag)) {
          return new ItemCkkbBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_ckkb is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCODELIST: {
        if ("layout/item_codelist_0".equals(tag)) {
          return new ItemCodelistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_codelist is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCOMPLETION1: {
        if ("layout/item_completion1_0".equals(tag)) {
          return new ItemCompletion1BindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_completion1 is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCOMPLETION2: {
        if ("layout/item_completion2_0".equals(tag)) {
          return new ItemCompletion2BindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_completion2 is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCONFIRM1: {
        if ("layout/item_confirm1_0".equals(tag)) {
          return new ItemConfirm1BindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_confirm1 is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCONFIRM2: {
        if ("layout/item_confirm2_0".equals(tag)) {
          return new ItemConfirm2BindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_confirm2 is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDETAILLIST: {
        if ("layout/item_detail_list_0".equals(tag)) {
          return new ItemDetailListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_detail_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDETAILS: {
        if ("layout/item_details_0".equals(tag)) {
          return new ItemDetailsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_details is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDHCJ: {
        if ("layout/item_dhcj_0".equals(tag)) {
          return new ItemDhcjBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_dhcj is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDHSJ: {
        if ("layout/item_dhsj_0".equals(tag)) {
          return new ItemDhsjBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_dhsj is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDISPATCHDETAILS: {
        if ("layout/item_dispatchdetails_0".equals(tag)) {
          return new ItemDispatchdetailsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_dispatchdetails is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMHWYD: {
        if ("layout/item_hwyd_0".equals(tag)) {
          return new ItemHwydBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_hwyd is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMINPUT: {
        if ("layout/item_input_0".equals(tag)) {
          return new ItemInputBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_input is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMINSPECTION: {
        if ("layout/item_inspection_0".equals(tag)) {
          return new ItemInspectionBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_inspection is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMLIST: {
        if ("layout/item_list_0".equals(tag)) {
          return new ItemListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPACKINGINSPETION: {
        if ("layout/item_packing_inspetion_0".equals(tag)) {
          return new ItemPackingInspetionBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_packing_inspetion is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPRODUCTIONLIST: {
        if ("layout/item_production_list_0".equals(tag)) {
          return new ItemProductionListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_production_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPURCHASE: {
        if ("layout/item_purchase_0".equals(tag)) {
          return new ItemPurchaseBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_purchase is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMQUALIFIED: {
        if ("layout/item_qualified_0".equals(tag)) {
          return new ItemQualifiedBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_qualified is invalid. Received: " + tag);
      }
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ITEMSTOCKCHECK: {
        if ("layout/item_stockcheck_0".equals(tag)) {
          return new ItemStockcheckBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_stockcheck is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMUNQUALIFIED: {
        if ("layout/item_unqualified_0".equals(tag)) {
          return new ItemUnqualifiedBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_unqualified is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(8);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "adapter");
      sKeys.put(2, "arrivalHeadBean");
      sKeys.put(3, "data");
      sKeys.put(4, "unqualifiedBean");
      sKeys.put(5, "user");
      sKeys.put(6, "bean");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(52);

    static {
      sKeys.put("layout/activity_areaselect_0", com.lkrh.storescontrol.R.layout.activity_areaselect);
      sKeys.put("layout/activity_bill_detail_0", com.lkrh.storescontrol.R.layout.activity_bill_detail);
      sKeys.put("layout/activity_bill_list_0", com.lkrh.storescontrol.R.layout.activity_bill_list);
      sKeys.put("layout/activity_chart_list_0", com.lkrh.storescontrol.R.layout.activity_chart_list);
      sKeys.put("layout/activity_claim_material_0", com.lkrh.storescontrol.R.layout.activity_claim_material);
      sKeys.put("layout/activity_confirm_0", com.lkrh.storescontrol.R.layout.activity_confirm);
      sKeys.put("layout/activity_confirmlist_0", com.lkrh.storescontrol.R.layout.activity_confirmlist);
      sKeys.put("layout/activity_confirmlist2_0", com.lkrh.storescontrol.R.layout.activity_confirmlist2);
      sKeys.put("layout/activity_help_0", com.lkrh.storescontrol.R.layout.activity_help);
      sKeys.put("layout/activity_index_0", com.lkrh.storescontrol.R.layout.activity_index);
      sKeys.put("layout/activity_inspectionlist_0", com.lkrh.storescontrol.R.layout.activity_inspectionlist);
      sKeys.put("layout/activity_inspectionlist2_0", com.lkrh.storescontrol.R.layout.activity_inspectionlist2);
      sKeys.put("layout/activity_login_0", com.lkrh.storescontrol.R.layout.activity_login);
      sKeys.put("layout/activity_logistics_distribution_0", com.lkrh.storescontrol.R.layout.activity_logistics_distribution);
      sKeys.put("layout/activity_manage_0", com.lkrh.storescontrol.R.layout.activity_manage);
      sKeys.put("layout/activity_material_0", com.lkrh.storescontrol.R.layout.activity_material);
      sKeys.put("layout/activity_order_change_0", com.lkrh.storescontrol.R.layout.activity_order_change);
      sKeys.put("layout/activity_packing_inspetion_0", com.lkrh.storescontrol.R.layout.activity_packing_inspetion);
      sKeys.put("layout/activity_print_0", com.lkrh.storescontrol.R.layout.activity_print);
      sKeys.put("layout/activity_production_list_0", com.lkrh.storescontrol.R.layout.activity_production_list);
      sKeys.put("layout/activity_production_order_0", com.lkrh.storescontrol.R.layout.activity_production_order);
      sKeys.put("layout/activity_productionwarehousing_0", com.lkrh.storescontrol.R.layout.activity_productionwarehousing);
      sKeys.put("layout/activity_put_list_0", com.lkrh.storescontrol.R.layout.activity_put_list);
      sKeys.put("layout/activity_putdetail_0", com.lkrh.storescontrol.R.layout.activity_putdetail);
      sKeys.put("layout/activity_qualified_list_0", com.lkrh.storescontrol.R.layout.activity_qualified_list);
      sKeys.put("layout/activity_report_0", com.lkrh.storescontrol.R.layout.activity_report);
      sKeys.put("layout/activity_stock_allocation_0", com.lkrh.storescontrol.R.layout.activity_stock_allocation);
      sKeys.put("layout/activity_stockcheck_0", com.lkrh.storescontrol.R.layout.activity_stockcheck);
      sKeys.put("layout/activity_supplement_list_0", com.lkrh.storescontrol.R.layout.activity_supplement_list);
      sKeys.put("layout/activity_test_0", com.lkrh.storescontrol.R.layout.activity_test);
      sKeys.put("layout/activity_testlist_0", com.lkrh.storescontrol.R.layout.activity_testlist);
      sKeys.put("layout/item_ckkb_0", com.lkrh.storescontrol.R.layout.item_ckkb);
      sKeys.put("layout/item_codelist_0", com.lkrh.storescontrol.R.layout.item_codelist);
      sKeys.put("layout/item_completion1_0", com.lkrh.storescontrol.R.layout.item_completion1);
      sKeys.put("layout/item_completion2_0", com.lkrh.storescontrol.R.layout.item_completion2);
      sKeys.put("layout/item_confirm1_0", com.lkrh.storescontrol.R.layout.item_confirm1);
      sKeys.put("layout/item_confirm2_0", com.lkrh.storescontrol.R.layout.item_confirm2);
      sKeys.put("layout/item_detail_list_0", com.lkrh.storescontrol.R.layout.item_detail_list);
      sKeys.put("layout/item_details_0", com.lkrh.storescontrol.R.layout.item_details);
      sKeys.put("layout/item_dhcj_0", com.lkrh.storescontrol.R.layout.item_dhcj);
      sKeys.put("layout/item_dhsj_0", com.lkrh.storescontrol.R.layout.item_dhsj);
      sKeys.put("layout/item_dispatchdetails_0", com.lkrh.storescontrol.R.layout.item_dispatchdetails);
      sKeys.put("layout/item_hwyd_0", com.lkrh.storescontrol.R.layout.item_hwyd);
      sKeys.put("layout/item_input_0", com.lkrh.storescontrol.R.layout.item_input);
      sKeys.put("layout/item_inspection_0", com.lkrh.storescontrol.R.layout.item_inspection);
      sKeys.put("layout/item_list_0", com.lkrh.storescontrol.R.layout.item_list);
      sKeys.put("layout/item_packing_inspetion_0", com.lkrh.storescontrol.R.layout.item_packing_inspetion);
      sKeys.put("layout/item_production_list_0", com.lkrh.storescontrol.R.layout.item_production_list);
      sKeys.put("layout/item_purchase_0", com.lkrh.storescontrol.R.layout.item_purchase);
      sKeys.put("layout/item_qualified_0", com.lkrh.storescontrol.R.layout.item_qualified);
      sKeys.put("layout/item_stockcheck_0", com.lkrh.storescontrol.R.layout.item_stockcheck);
      sKeys.put("layout/item_unqualified_0", com.lkrh.storescontrol.R.layout.item_unqualified);
    }
  }
}
