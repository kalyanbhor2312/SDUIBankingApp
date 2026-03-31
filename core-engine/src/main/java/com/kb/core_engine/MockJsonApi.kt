package com.kb.core_engine

import com.kb.core.ScreenIds

object MockJsonApi {

    private val login =
        """
        {
          "id": "login",
          "metadata": {"schemaVersion":"1.0","screenType":"login"},
          "topBar": {
            "show": true,
            "title":"Architect",
            "leading":"ICON",
            "leadingIcon":"bank",
            "showSearch": false,
            "showNotifications": false,
            "trailingProfile": false
          },
          "components": [
            {"type":"header","id":"h1","title":"Secure\nPortal","subtitle":"Access your institutional wealth management dashboard.","style":{"margin":{"top":20,"start":4,"end":4,"bottom":8}}},
            {"type":"text_field","id":"f1","label":"Username","placeholder":"Institutional ID or Email","key":"user"},
            {"type":"text_field","id":"f2","label":"Password","placeholder":"••••••••••••","key":"pass","isPassword":true,"trailingText":"Forgot?"},
            {"type":"button","id":"b1","text":"Login","action":{
                "type":"api_call",
                "url":"/api/login",
                "method":"POST",
                "bodyKeys":["user", "pass"],
                "onSuccess":{"type":"navigate","destination":"home"},
                "onFailure":{"type":"show_toast","message":"Invalid credentials. Use abcd / 123"}
            }},
            {"type":"social_login","id":"s1","label":"Authentication","options":[
              {"type":"fingerprint","action":{"type":"show_toast","message":"Fingerprint"}},
              {"type":"face","action":{"type":"show_toast","message":"Face ID"}}
            ]},
            {"type":"footer","id":"ft1","text":"New to the firm? ","actionText":"Inquire about an account","action":{"type":"show_toast","message":"Inquiry"}}
          ]
        }
        """.trimIndent()

    private val home =
        """
        {
          "id": "home",
          "metadata": {"schemaVersion":"1.0","screenType":"home"},
          "topBar": {"show": true, "title":"Architect", "leading":"PROFILE", "showSearch": true, "showNotifications": true},
          "bottomNavigation": {
            "selectedIndex": 0,
            "items": [
              {"id":"nav_home","label":"Home","icon":"home","action":{"type":"navigate","destination":"home"}},
              {"id":"nav_payments","label":"Payments","icon":"payments","action":{"type":"navigate","destination":"payments"}},
              {"id":"nav_accounts","label":"Accounts","icon":"accounts","action":{"type":"navigate","destination":"accounts"}},
              {"id":"nav_profile","label":"Profile","icon":"profile","action":{"type":"navigate","destination":"profile"}}
            ]
          },
          "components": [
            {"type":"home_net_worth_summary","id":"home_summary","label":"Total Net Worth","totalAmount":"${'$'}142,850.42","growthText":"↗ +2.4%","comparisonText":"vs last month","style":{"margin":{"top":8,"bottom":4}}},
            {"type":"home_action_tiles","id":"home_actions","style":{"margin":{"bottom":8}},"actions":[
              {"label":"Transfer","icon":"transfer","isPrimary":true,"action":{"type":"navigate","destination":"payments"}},
              {"label":"Pay Bills","icon":"bills","isPrimary":false,"action":{"type":"show_toast","message":"Bill pay coming soon"}}
            ]},
            {"type":"relationships_section","id":"home_relationships","title":"Relationships","actionText":"Manage Accounts","items":[
              {"id":"rel_checking","title":"Elite Checking","amount":"${'$'}42,301.15","subtitle":"Available","maskedNumber":"9012","icon":"wallet"},
              {"id":"rel_savings","title":"High-Yield Savings","amount":"${'$'}100,549.27","subtitle":"4.50% APY","maskedNumber":"4481","icon":"piggy"}
            ]},
            {"type":"portfolio_growth_panel","id":"home_growth","title":"Portfolio\nGrowth","subtitle":"Insights from your\nlast 6 months","periodLabel":"Last 6 Months"},
            {"type":"home_activity_section","id":"home_activity","title":"Activity","actionText":"View All","items":[
              {"id":"h_act_1","icon":"shopping","title":"Apple Store","accountName":"Electronics","category":"Electronics","date":"Today","amount":"-${'$'}1,299.00","isPositive":false},
              {"id":"h_act_2","icon":"deposit","title":"Salary Deposit","accountName":"Income","category":"Income","date":"Oct 15","amount":"+${'$'}8,450.00","isPositive":true},
              {"id":"h_act_3","icon":"dining","title":"The Oak Bistro","accountName":"Dining","category":"Dining","date":"Oct 14","amount":"-${'$'}84.20","isPositive":false},
              {"id":"h_act_4","icon":"transport","title":"Tesla Supercharger","accountName":"Transport","category":"Transport","date":"Oct 12","amount":"-${'$'}22.50","isPositive":false}
            ]},
            {"type":"promo_banner","id":"home_offer","overline":"Exclusive Offer","title":"Unlock Private Wealth Advisory\nServices","actionText":"Learn more"}
          ]
        }
        """.trimIndent()

    private val payments =
        """
        {
          "id":"payments",
          "metadata": {"schemaVersion":"1.0","screenType":"payments"},
          "topBar": {"show": true, "title":"Architect", "leading":"PROFILE", "showSearch": false, "showNotifications": true},
          "bottomNavigation": {
            "selectedIndex": 1,
            "items": [
              {"id":"nav_home","label":"Home","icon":"home","action":{"type":"navigate","destination":"home"}},
              {"id":"nav_payments","label":"Payments","icon":"payments","action":{"type":"navigate","destination":"payments"}},
              {"id":"nav_accounts","label":"Accounts","icon":"accounts","action":{"type":"navigate","destination":"accounts"}},
              {"id":"nav_profile","label":"Profile","icon":"profile","action":{"type":"navigate","destination":"profile"}}
            ]
          },
          "components": [
            {"type":"payment_step_header","id":"pay_step","stepLabel":"Step 01","title":"Transfer","nextStepLabel":"Confirmation"},
            {"type":"source_account_selector","id":"pay_source","label":"Select Source Account","accountName":"Wealth Management","maskedNumber":"8829","availableBalance":"142,850.00","currencySymbol":"${'$'}","action":{"type":"show_toast","message":"Source account selector coming soon"}},
            {"type":"amount_input_card","id":"pay_amount","label":"Transfer Amount","key":"transfer_amount","placeholder":"0.00","presetAmounts":["1,000","5,000","10,000"],"currencySymbol":"${'$'}"},
            {"type":"note_input","id":"pay_note","label":"Add Note (Optional)","key":"transfer_note","placeholder":"e.g. Q3 Studio Renovation..."},
            {"type":"button","id":"pay_cta","text":"Confirm & Proceed","action":{"type":"show_toast","message":"Proceeding with transfer"}},
            {"type":"section_header","id":"pay_beneficiary_header","title":"Beneficiary"},
            {"type":"beneficiary_grid","id":"pay_beneficiaries","title":"Recent","actionText":"View All","addNewLabel":"New Recipient","addNewAction":{"type":"navigate","destination":"add_beneficiary"},"items":[
              {"id":"b1","name":"Alex Sterling","subtitle":"Studio Alpha","initials":"AS","isSelected":false,"action":{"type":"show_toast","message":"Selected Alex Sterling"}},
              {"id":"b2","name":"Mila Novak","subtitle":"Novak & Co","initials":"MN","isSelected":true,"action":{"type":"show_toast","message":"Selected Mila Novak"}},
              {"id":"b3","name":"Julian Drax","subtitle":"Personal","initials":"JD","isSelected":false,"action":{"type":"show_toast","message":"Selected Julian Drax"}}
            ]},
            {"type":"transfer_limit_card","id":"pay_limit","title":"Transfer Limit","amount":"${'$'}250,000","suffix":"Daily Remaining"}
          ]
        }
        """.trimIndent()

    private val addBeneficiary =
        """
        {
          "id":"add_beneficiary",
          "metadata": {"schemaVersion":"1.0","screenType":"add_beneficiary"},
          "topBar": {
            "show": true,
            "title":"Architect",
            "leading":"BACK",
            "showSearch": false,
            "showNotifications": true,
            "trailingProfile": true,
            "backAction": {"type":"navigate","destination":"payments"}
          },
          "components": [
            {"type":"section_header","id":"sec_security","title":"Security & Identity"},
            {"type":"header","id":"h_add_ben","title":"Add Beneficiary","subtitle":"Securely save account details for faster recurring transfers and payments."},
            {"type":"text_field","id":"f_account_name","label":"Account Name","placeholder":"John Doe","key":"beneficiary_account_name","description":"Must match the legal name on the destination account."},
            {"type":"dropdown","id":"dd_bank_name","label":"Bank Name","placeholder":"Select Institution","key":"beneficiary_bank_name","options":["Wells Fargo Bank","Bank of America","Chase Bank","Citibank","US Bank","Other Institution"]},
            {"type":"text_field","id":"f_account_number","label":"Account Number","placeholder":"0000000000","key":"beneficiary_account_number"},
            {"type":"text_field","id":"f_nickname","label":"Nickname (Optional)","placeholder":"e.g. Rent Payment","key":"beneficiary_nickname","description":"A short name to help you identify this contact."},
            {"type":"info_banner","id":"banner_security","title":"Secure Verification","description":"Architect verifies all beneficiary accounts against global banking standards to prevent fraud."},
            {"type":"button","id":"btn_add_beneficiary","text":"Save Beneficiary","action":{"type":"navigate","destination":"payments"}}
          ]
        }
        """.trimIndent()

    private val accounts =
        """
        {
          "id":"accounts",
          "metadata": {"schemaVersion":"1.0","screenType":"accounts"},
          "topBar": {"show": true, "title":"Architect", "leading":"PROFILE", "showSearch": false, "showNotifications": true},
          "bottomNavigation": {
            "selectedIndex": 2,
            "items": [
              {"id":"nav_home","label":"Home","icon":"home","action":{"type":"navigate","destination":"home"}},
              {"id":"nav_payments","label":"Payments","icon":"payments","action":{"type":"navigate","destination":"payments"}},
              {"id":"nav_accounts","label":"Accounts","icon":"accounts","action":{"type":"navigate","destination":"accounts"}},
              {"id":"nav_profile","label":"Profile","icon":"profile","action":{"type":"navigate","destination":"profile"}}
            ]
          },
          "components": [
            {"type":"accounts_balance_summary","id":"acc_summary","label":"Total Combined Balance","totalAmount":"${'$'}1,248,392.42","growthText":"↗ +2.4% vs last month"},
            {"type":"portfolio_account_card","id":"acc_checking","icon":"bank","maskedNumber":"8821","title":"Elite Checking","subtitle":"Primary operating account","primaryMetricLabel":"Current Balance","primaryMetricValue":"${'$'}42,905.12","secondaryMetricLabel":"Available Balance","secondaryMetricValue":"${'$'}41,500.00","actionText":"Manage","variant":"LIGHT","action":{"type":"show_toast","message":"Manage Elite Checking"}},
            {"type":"portfolio_account_card","id":"acc_savings","icon":"savings","maskedNumber":"4409","title":"High-Yield\nSavings","subtitle":"Wealth Reserve Fund","primaryMetricLabel":"Total Balance","primaryMetricValue":"${'$'}285,487.30","secondaryMetricLabel":"Interest Earned YTD","secondaryMetricValue":"+${'$'}9,214.50","actionText":"Manage","badgeText":"4.85%\nAPY","variant":"LIGHT_BADGE","action":{"type":"show_toast","message":"Manage High-Yield Savings"}},
            {"type":"portfolio_account_card","id":"acc_wealth","icon":"wallet","maskedNumber":"0012","title":"Wealth Management","subtitle":"Managed Assets Portfolio","primaryMetricLabel":"Total Portfolio Value","primaryMetricValue":"${'$'}920,000.00","secondaryMetricLabel":"Allocation","secondaryMetricValue":"","actionText":"Manage Portfolio","variant":"DARK","footnotes":[{"label":"Equity","value":"72% ${'$'}662.4k"},{"label":"Cash","value":"28% ${'$'}257.6k"}],"action":{"type":"show_toast","message":"Manage Wealth Management"}},
            {"type":"recent_activity_section","id":"acc_activity","title":"Recent Activity","subtitle":"Real-time updates across all assets","actionText":"View\nStatement","items":[
              {"id":"act1","icon":"shopping","title":"Apple Store\nInfinite Loop","accountName":"Elite Checking","category":"Purchase","date":"Today","amount":"-${'$'}1,299.00","isPositive":false},
              {"id":"act2","icon":"market","title":"Dividend\nPayment - NVDA","accountName":"Wealth Management","category":"Income","date":"Yesterday","amount":"+${'$'}420.50","isPositive":true},
              {"id":"act3","icon":"dining","title":"The Modern\nOmakase","accountName":"Elite Checking","category":"Dining","date":"Oct 12","amount":"-${'$'}650.00","isPositive":false},
              {"id":"act4","icon":"service","title":"Bloomberg\nProfessional","accountName":"Elite Checking","category":"Service","date":"Oct 10","amount":"-${'$'}2,000.00","isPositive":false}
            ]}
          ]
        }
        """.trimIndent()

    private val profile =
        """
        {
          "id":"profile",
          "metadata": {"schemaVersion":"1.0","screenType":"profile"},
          "topBar": {"show": true, "title":"Architect", "leading":"PROFILE", "showSearch": false, "showNotifications": true},
          "bottomNavigation": {
            "selectedIndex": 3,
            "items": [
              {"id":"nav_home","label":"Home","icon":"home","action":{"type":"navigate","destination":"home"}},
              {"id":"nav_payments","label":"Payments","icon":"payments","action":{"type":"navigate","destination":"payments"}},
              {"id":"nav_accounts","label":"Accounts","icon":"accounts","action":{"type":"navigate","destination":"accounts"}},
              {"id":"nav_profile","label":"Profile","icon":"profile","action":{"type":"navigate","destination":"profile"}}
            ]
          },
          "components": [
            {"type":"profile_header","id":"prof_h","name":"Alexander Sterling","memberSince":"January 2022", "imageUrl": "https://images.unsplash.com/photo-1560250097-0b93528c311a?q=80&w=256&h=256&auto=format&fit=crop"},
            {"type":"section_header","id":"sec_personal","title":"Personal Information"},
            {"type":"info_card","id":"info_email","label":"Primary Email","value":"a.sterling@architect.com"},
            {"type":"info_card","id":"info_phone","label":"Phone Number","value":"+1 (555) 892-4410"},
            {"type":"section_header","id":"sec_security","title":"Security & Access"},
            {"type":"settings_group","id":"grp_security","items":[
              {"icon":"password","title":"Change Password","trailingType":"ARROW"},
              {"icon":"biometric","title":"Biometric Login","subtitle":"Use FaceID or TouchID","trailingType":"SWITCH"}
            ]},
            {"type":"section_header","id":"sec_prefs","title":"App Preferences"},
            {"type":"settings_group","id":"grp_prefs","items":[
              {"icon":"notifications","title":"Push Notifications","trailingType":"ARROW"},
              {"icon":"appearance","title":"Appearance","subtitle":"System Default","trailingType":"ARROW"},
              {"icon":"language","title":"Language","trailingType":"TEXT","trailingText":"English (US)"}
            ]},
            {"type":"button","id":"b_logout","text":"Log Out","button_type":"LOGOUT","action":{"type":"navigate","destination":"login"}},
            {"type":"footer","id":"ft_profile","text":"Architect v4.2.0 • Secured by Deep Ledger"}
          ]
        }
        """.trimIndent()

    val screenPayloads: Map<String, String> = mapOf(
        ScreenIds.LOGIN to login,
        ScreenIds.HOME to home,
        ScreenIds.PAYMENTS to payments,
        ScreenIds.ADD_BENEFICIARY to addBeneficiary,
        ScreenIds.ACCOUNTS to accounts,
        ScreenIds.PROFILE to profile
    )
}
