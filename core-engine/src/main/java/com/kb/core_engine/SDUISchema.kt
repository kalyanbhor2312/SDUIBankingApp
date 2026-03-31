package com.kb.core_engine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Top-level response for a Server-Driven UI screen.
 */
@Serializable
data class SDUIScreen(
    val id: String,
    val title: String? = null,
    val components: List<SDUIComponent>,
    val topBar: SDUITopBar? = null,
    val bottomNavigation: SDUIBottomNavigation? = null,
    val onLoadActions: List<SDUIAction>? = null,
    val metadata: Map<String, String>? = null
)

@Serializable
data class SDUITopBar(
    val show: Boolean = true,
    val title: String = "Architect",
    val leading: TopBarLeading = TopBarLeading.LOGO,
    val leadingIcon: String? = null,
    val showSearch: Boolean = false,
    val showNotifications: Boolean = true,
    val trailingProfile: Boolean = false,
    val backAction: SDUIAction? = null
)

@Serializable
enum class TopBarLeading { LOGO, PROFILE, BACK, ICON, NONE }

/**
 * Bottom Navigation configuration.
 */
@Serializable
data class SDUIBottomNavigation(
    val items: List<BottomNavItem>,
    val selectedIndex: Int = 0
)

@Serializable
data class BottomNavItem(
    val id: String,
    val label: String,
    val icon: String,
    val action: SDUIAction
)

/**
 * Sealed class representing all possible UI components.
 */
@Serializable
sealed class SDUIComponent {
    abstract val id: String
    abstract val style: SDUIStyle?

    @Serializable
    @SerialName("header")
    data class Header(
        override val id: String,
        val title: String,
        val subtitle: String? = null,
        val overline: String? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("profile_header")
    data class ProfileHeader(
        override val id: String,
        val name: String,
        val memberSince: String,
        val imageUrl: String? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("section_header")
    data class SectionHeader(
        override val id: String,
        val title: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("info_card")
    data class InfoCard(
        override val id: String,
        val label: String,
        val value: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("settings_group")
    data class SettingsGroup(
        override val id: String,
        val items: List<SettingsItemData>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("text_field")
    data class TextField(
        override val id: String,
        val label: String,
        val placeholder: String,
        val key: String, // Key to use when submitting form
        val trailingText: String? = null,
        val isPassword: Boolean = false,
        val description: String? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("dropdown")
    data class Dropdown(
        override val id: String,
        val label: String,
        val placeholder: String,
        val key: String,
        val options: List<String>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("info_banner")
    data class InfoBanner(
        override val id: String,
        val title: String,
        val description: String,
        val icon: String? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("button")
    data class Button(
        override val id: String,
        val text: String,
        val action: SDUIAction,
        @SerialName("button_type")
        val buttonType: ButtonType = ButtonType.PRIMARY,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("payment_step_header")
    data class PaymentStepHeader(
        override val id: String,
        val stepLabel: String,
        val title: String,
        val nextStepLabel: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("source_account_selector")
    data class SourceAccountSelector(
        override val id: String,
        val label: String,
        val accountName: String,
        val maskedNumber: String,
        val availableBalance: String,
        val currencySymbol: String = "$",
        val action: SDUIAction? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("amount_input_card")
    data class AmountInputCard(
        override val id: String,
        val label: String,
        val key: String,
        val placeholder: String,
        val presetAmounts: List<String>,
        val currencySymbol: String = "$",
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("note_input")
    data class NoteInput(
        override val id: String,
        val label: String,
        val key: String,
        val placeholder: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("beneficiary_grid")
    data class BeneficiaryGrid(
        override val id: String,
        val title: String,
        val actionText: String,
        val items: List<BeneficiaryItem>,
        val addNewLabel: String,
        val addNewAction: SDUIAction? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("transfer_limit_card")
    data class TransferLimitCard(
        override val id: String,
        val title: String,
        val amount: String,
        val suffix: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("balance_card")
    data class BalanceCard(
        override val id: String,
        val balance: String,
        val currency: String,
        val label: String,
        val action: SDUIAction? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("accounts_balance_summary")
    data class AccountsBalanceSummary(
        override val id: String,
        val label: String,
        val totalAmount: String,
        val growthText: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("home_net_worth_summary")
    data class HomeNetWorthSummary(
        override val id: String,
        val label: String,
        val totalAmount: String,
        val growthText: String,
        val comparisonText: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("home_action_tiles")
    data class HomeActionTiles(
        override val id: String,
        val actions: List<HomeActionItem>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("relationships_section")
    data class RelationshipsSection(
        override val id: String,
        val title: String,
        val actionText: String,
        val items: List<RelationshipItem>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("portfolio_growth_panel")
    data class PortfolioGrowthPanel(
        override val id: String,
        val title: String,
        val subtitle: String,
        val periodLabel: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("home_activity_section")
    data class HomeActivitySection(
        override val id: String,
        val title: String,
        val actionText: String,
        val items: List<ActivityItem>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("promo_banner")
    data class PromoBanner(
        override val id: String,
        val overline: String,
        val title: String,
        val actionText: String,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("portfolio_account_card")
    data class PortfolioAccountCard(
        override val id: String,
        val icon: String,
        val maskedNumber: String,
        val title: String,
        val subtitle: String,
        val primaryMetricLabel: String,
        val primaryMetricValue: String,
        val secondaryMetricLabel: String,
        val secondaryMetricValue: String,
        val actionText: String,
        val action: SDUIAction? = null,
        val badgeText: String? = null,
        val footnotes: List<PortfolioFootnote> = emptyList(),
        val variant: PortfolioCardVariant = PortfolioCardVariant.LIGHT,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("recent_activity_section")
    data class RecentActivitySection(
        override val id: String,
        val title: String,
        val subtitle: String,
        val actionText: String,
        val items: List<ActivityItem>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("transaction_list")
    data class TransactionList(
        override val id: String,
        val transactions: List<TransactionItem>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("quick_actions")
    data class QuickActions(
        override val id: String,
        val actions: List<QuickActionItem>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("social_login")
    data class SocialLogin(
        override val id: String,
        val label: String,
        val options: List<SocialOption>,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()

    @Serializable
    @SerialName("footer")
    data class Footer(
        override val id: String,
        val text: String,
        val actionText: String? = null,
        val action: SDUIAction? = null,
        override val style: SDUIStyle? = null
    ) : SDUIComponent()
}

@Serializable
enum class ButtonType { PRIMARY, SECONDARY, TEXT, LOGOUT }

@Serializable
enum class TrailingType { ARROW, SWITCH, TEXT }

@Serializable
data class SettingsItemData(
    val icon: String,
    val title: String,
    val subtitle: String? = null,
    val trailingType: TrailingType = TrailingType.ARROW,
    val trailingText: String? = null,
    val action: SDUIAction? = null
)

@Serializable
data class SocialOption(val type: String, val action: SDUIAction)

@Serializable
data class TransactionItem(
    val title: String,
    val amount: String,
    val date: String,
    val type: String, // CREDIT/DEBIT
    val icon: String? = null
)

@Serializable
data class QuickActionItem(
    val label: String,
    val icon: String,
    val action: SDUIAction
)

@Serializable
data class BeneficiaryItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val initials: String,
    val isSelected: Boolean = false,
    val action: SDUIAction? = null
)

@Serializable
data class PortfolioFootnote(
    val label: String,
    val value: String
)

@Serializable
data class HomeActionItem(
    val label: String,
    val icon: String,
    val isPrimary: Boolean = false,
    val action: SDUIAction
)

@Serializable
data class RelationshipItem(
    val id: String,
    val title: String,
    val amount: String,
    val subtitle: String,
    val maskedNumber: String,
    val icon: String
)

@Serializable
data class ActivityItem(
    val id: String,
    val icon: String,
    val title: String,
    val accountName: String,
    val category: String,
    val date: String,
    val amount: String,
    val isPositive: Boolean = false
)

@Serializable
enum class PortfolioCardVariant { LIGHT, LIGHT_BADGE, DARK }

/**
 * Enhanced styling properties.
 */
@Serializable
data class SDUIStyle(
    val backgroundColor: String? = null,
    val textColor: String? = null,
    val padding: Padding? = null,
    val margin: Padding? = null,
    val cornerRadius: Int? = null,
    val elevation: Int? = null
)

@Serializable
data class Padding(
    val top: Int = 0,
    val bottom: Int = 0,
    val start: Int = 0,
    val end: Int = 0
)

/**
 * Scalable Action System.
 */
@Serializable
sealed class SDUIAction {
    @Serializable
    @SerialName("navigate")
    data class Navigate(
        val destination: String,
        val params: Map<String, String>? = null,
        val clearStack: Boolean = false
    ) : SDUIAction()

    @Serializable
    @SerialName("api_call")
    data class ApiCall(
        val url: String,
        val method: String = "GET",
        val bodyKeys: List<String>? = null, // Which form fields to include in body
        val onSuccess: SDUIAction? = null,
        val onFailure: SDUIAction? = null
    ) : SDUIAction()

    @Serializable
    @SerialName("show_toast")
    data class ShowToast(val message: String) : SDUIAction()

    @Serializable
    @SerialName("open_url")
    data class OpenUrl(val url: String) : SDUIAction()
}
