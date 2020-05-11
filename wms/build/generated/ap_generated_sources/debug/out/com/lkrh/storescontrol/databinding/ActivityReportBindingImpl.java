package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityReportBindingImpl extends ActivityReportBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_title1, 2);
        sViewsWithIds.put(R.id.iv_scan, 3);
        sViewsWithIds.put(R.id.et_code, 4);
        sViewsWithIds.put(R.id.tv_title2, 5);
        sViewsWithIds.put(R.id.iv_scan2, 6);
        sViewsWithIds.put(R.id.et_ctuopan, 7);
        sViewsWithIds.put(R.id.tv_cmocode, 8);
        sViewsWithIds.put(R.id.tv_cInvName, 9);
        sViewsWithIds.put(R.id.tv_cOpdesc, 10);
        sViewsWithIds.put(R.id.et_cusercode, 11);
        sViewsWithIds.put(R.id.b_user, 12);
        sViewsWithIds.put(R.id.tv_imoqty, 13);
        sViewsWithIds.put(R.id.ll_qualified, 14);
        sViewsWithIds.put(R.id.et_ihgqty, 15);
        sViewsWithIds.put(R.id.et_ctuopan1, 16);
        sViewsWithIds.put(R.id.ll_unqualified, 17);
        sViewsWithIds.put(R.id.et_ibhgqty, 18);
        sViewsWithIds.put(R.id.et_ctuopan2, 19);
        sViewsWithIds.put(R.id.et_cmemo2, 20);
        sViewsWithIds.put(R.id.b_des, 21);
        sViewsWithIds.put(R.id.b_submit, 22);
        sViewsWithIds.put(R.id.b_material, 23);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityReportBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private ActivityReportBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[21]
            , (android.widget.Button) bindings[23]
            , (android.widget.Button) bindings[22]
            , (android.widget.Button) bindings[12]
            , (android.widget.EditText) bindings[20]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[16]
            , (android.widget.EditText) bindings[19]
            , (android.widget.EditText) bindings[11]
            , (android.widget.EditText) bindings[18]
            , (android.widget.EditText) bindings[15]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[5]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}