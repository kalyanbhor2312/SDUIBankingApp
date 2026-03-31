package com.kb.core_engine
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kb.designsystem.components.*
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import com.kb.designsystem.ui.theme.ArchitectBackground
import com.kb.designsystem.ui.theme.ArchitectPrimary
import com.kb.designsystem.ui.theme.ArchitectTeal
import com.kb.designsystem.ui.theme.ArchitectTextSecondary

@Composable
fun ComponentRenderer(
    components: List<SDUIComponent>,
    onAction: (SDUIAction) -> Unit,
    formData: Map<String, String> = emptyMap(),
    onFieldChanged: (String, String) -> Unit = { _, _ -> }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ArchitectBackground)
    ) {
        items(items =components, key = { it.id },)
        { component ->
            StyledComponentContainer(style = component.style) {
                when (component) {
                    is SDUIComponent.Header -> {
                        Header(
                            title = component.title,
                            subtitle = component.subtitle,
                            overline = component.overline
                        )
                    }

                    is SDUIComponent.ProfileHeader -> {
                        ProfileHeader(
                            name = component.name,
                            memberSince = component.memberSince,
                            imageUrl = component.imageUrl
                        )
                    }

                    is SDUIComponent.SectionHeader -> {
                        SectionHeader(title = component.title)
                    }

                    is SDUIComponent.InfoCard -> {
                        InfoCard(
                            label = component.label,
                            value = component.value
                        )
                    }

                    is SDUIComponent.SettingsGroup -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(Color.White)
                        ) {
                            component.items.forEach { item ->
                                SettingsItem(
                                    icon = item.icon,
                                    title = item.title,
                                    subtitle = item.subtitle,
                                    trailingType = item.trailingType.name,
                                    trailingText = item.trailingText,
                                    onClick = { item.action?.let(onAction) }
                                )
                            }
                        }
                    }

                    is SDUIComponent.TextField -> {
                        InputTextField(
                            value = formData[component.key].orEmpty(),
                            onValueChange = { onFieldChanged(component.key, it) },
                            label = component.label,
                            placeholder = component.placeholder,
                            trailingText = component.trailingText,
                            isPassword = component.isPassword,
                            description = component.description
                        )
                    }

                    is SDUIComponent.Dropdown -> {
                        Dropdown(
                            label = component.label,
                            placeholder = component.placeholder,
                            options = component.options,
                            selectedOption = formData[component.key].orEmpty(),
                            onOptionSelected = { onFieldChanged(component.key, it) }
                        )
                    }

                    is SDUIComponent.InfoBanner -> {
                        Banner(
                            title = component.title,
                            description = component.description
                        )
                    }

                    is SDUIComponent.Button -> {
                        StandardButton(
                            text = component.text,
                            onClick = { onAction(component.action) },
                            type = component.buttonType.name
                        )
                    }

                    is SDUIComponent.PaymentStepHeader -> {
                        TransferStepHeader(
                            stepLabel = component.stepLabel,
                            title = component.title,
                            nextStepLabel = component.nextStepLabel
                        )
                    }

                    is SDUIComponent.SourceAccountSelector -> {
                        SourceAccountCard(
                            label = component.label,
                            accountName = component.accountName,
                            maskedNumber = component.maskedNumber,
                            balance = component.availableBalance,
                            currencySymbol = component.currencySymbol,
                            onClick = { component.action?.let(onAction) }
                        )
                    }

                    is SDUIComponent.AmountInputCard -> {
                        AmountInputCard(
                            label = component.label,
                            value = formData[component.key].orEmpty(),
                            placeholder = component.placeholder,
                            currencySymbol = component.currencySymbol,
                            presetAmounts = component.presetAmounts,
                            onValueChange = { onFieldChanged(component.key, it) }
                        )
                    }

                    is SDUIComponent.NoteInput -> {
                        NoteInput(
                            label = component.label,
                            value = formData[component.key].orEmpty(),
                            placeholder = component.placeholder,
                            onValueChange = { onFieldChanged(component.key, it) }
                        )
                    }

                    is SDUIComponent.BeneficiaryGrid -> {
                        BeneficiarySection(
                            title = component.title,
                            actionText = component.actionText,
                            items = component.items.map {
                                BeneficiaryModel(
                                    id = it.id,
                                    name = it.name,
                                    subtitle = it.subtitle,
                                    initials = it.initials,
                                    isSelected = it.isSelected
                                )
                            },
                            addNewLabel = component.addNewLabel,
                            onItemClick = { beneficiaryId ->
                                component.items.firstOrNull { it.id == beneficiaryId }?.action?.let(onAction)
                            },
                            onAddNew = { component.addNewAction?.let(onAction) }
                        )
                    }

                    is SDUIComponent.TransferLimitCard -> {
                        LimitCard(
                            title = component.title,
                            amount = component.amount,
                            suffix = component.suffix
                        )
                    }

                    is SDUIComponent.SocialLogin -> {
                        Login(
                            label = component.label,
                            options = component.options.map { SocialOptionData(it.type) },
                            onOptionClick = { type ->
                                component.options.find { it.type == type }?.action?.let(onAction)
                            }
                        )
                    }

                    is SDUIComponent.Footer -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = component.text,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = ArchitectTextSecondary
                                )
                                component.actionText?.let {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = ArchitectPrimary,
                                        modifier = Modifier.clickable {
                                            component.action?.let(onAction)
                                        }
                                    )
                                }
                            }
                        }
                    }

                    is SDUIComponent.BalanceCard -> {
                        AccountCard(
                            title = component.label,
                            balance = "${component.currency}${component.balance}",
                            manageAction = { component.action?.let(onAction) }
                        )
                    }

                    is SDUIComponent.AccountsBalanceSummary -> {
                        BalanceSummary(
                            label = component.label,
                            totalAmount = component.totalAmount,
                            growthText = component.growthText
                        )
                    }

                    is SDUIComponent.HomeNetWorthSummary -> {
                        HomeNetWorth(
                            label = component.label,
                            totalAmount = component.totalAmount,
                            growthText = component.growthText,
                            comparisonText = component.comparisonText
                        )
                    }

                    is SDUIComponent.HomeActionTiles -> {
                        HomeActionTiles(
                            actions = component.actions.map {
                                HomeActionModel(
                                    id = it.label,
                                    label = it.label,
                                    icon = it.icon,
                                    isPrimary = it.isPrimary
                                )
                            },
                            onActionClick = { selectedId ->
                                component.actions.firstOrNull { it.label == selectedId }?.action?.let(onAction)
                            }
                        )
                    }

                    is SDUIComponent.RelationshipsSection -> {
                        RelationshipsSection(
                            title = component.title,
                            actionText = component.actionText,
                            items = component.items.map {
                                RelationshipModel(
                                    title = it.title,
                                    amount = it.amount,
                                    subtitle = it.subtitle,
                                    maskedNumber = it.maskedNumber,
                                    icon = it.icon
                                )
                            }
                        )
                    }

                    is SDUIComponent.PortfolioGrowthPanel -> {
                        PortfolioGrowthPanel(
                            title = component.title,
                            subtitle = component.subtitle,
                            periodLabel = component.periodLabel
                        )
                    }

                    is SDUIComponent.HomeActivitySection -> {
                        HomeActivitySection(
                            title = component.title,
                            actionText = component.actionText,
                            items = component.items.map {
                                ActivityModel(
                                    icon = it.icon,
                                    title = it.title,
                                    accountName = it.accountName,
                                    category = it.category,
                                    date = it.date,
                                    amount = it.amount,
                                    isPositive = it.isPositive
                                )
                            }
                        )
                    }

                    is SDUIComponent.PromoBanner -> {
                        PromoBanner(
                            overline = component.overline,
                            title = component.title,
                            actionText = component.actionText
                        )
                    }

                    is SDUIComponent.PortfolioAccountCard -> {
                        PortfolioAccountCard(
                            icon = component.icon,
                            maskedNumber = component.maskedNumber,
                            title = component.title,
                            subtitle = component.subtitle,
                            primaryMetricLabel = component.primaryMetricLabel,
                            primaryMetricValue = component.primaryMetricValue,
                            secondaryMetricLabel = component.secondaryMetricLabel,
                            secondaryMetricValue = component.secondaryMetricValue,
                            actionText = component.actionText,
                            badgeText = component.badgeText,
                            footnotes = component.footnotes.map { it.label to it.value },
                            variant = component.variant.name,
                            onActionClick = { component.action?.let(onAction) }
                        )
                    }

                    is SDUIComponent.RecentActivitySection -> {
                        RecentActivitySection(
                            title = component.title,
                            subtitle = component.subtitle,
                            actionText = component.actionText,
                            items = component.items.map {
                                ActivityModel(
                                    icon = it.icon,
                                    title = it.title,
                                    accountName = it.accountName,
                                    category = it.category,
                                    date = it.date,
                                    amount = it.amount,
                                    isPositive = it.isPositive
                                )
                            }
                        )
                    }

                    is SDUIComponent.TransactionList -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(Color.White)
                        ) {
                            component.transactions.forEach { transaction ->
                                TransactionItem(
                                    title = transaction.title,
                                    subtitle = "${transaction.type} • ${transaction.date}",
                                    amount = transaction.amount,
                                    isPositive = transaction.type == "CREDIT"
                                )
                            }
                        }
                    }

                    is SDUIComponent.QuickActions -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            component.actions.forEach { actionItem ->
                                ArchitectQuickAction(
                                    label = actionItem.label,
                                    onClick = { onAction(actionItem.action) }
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun StyledComponentContainer(
    style: SDUIStyle?,
    content: @Composable () -> Unit
) {
    val margin = style?.margin
    val padding = style?.padding
    val backgroundColor = style?.backgroundColor?.toComposeColor()
    val cornerRadius = (style?.cornerRadius ?: 0).dp
    val elevation = (style?.elevation ?: 0).dp

    val outerModifier = Modifier.padding(
        start = (margin?.start ?: 0).dp,
        top = (margin?.top ?: 0).dp,
        end = (margin?.end ?: 0).dp,
        bottom = (margin?.bottom ?: 0).dp
    )

    if (backgroundColor != null || elevation > 0.dp) {
        Card(
            modifier = outerModifier.fillMaxWidth(),
            shape = RoundedCornerShape(cornerRadius),
            colors = CardDefaults.cardColors(containerColor = backgroundColor ?: Color.Transparent),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation)
        ) {
            Box(
                modifier = Modifier.padding(
                    start = (padding?.start ?: 0).dp,
                    top = (padding?.top ?: 0).dp,
                    end = (padding?.end ?: 0).dp,
                    bottom = (padding?.bottom ?: 0).dp
                )
            ) {
                content()
            }
        }
    } else {
        Box(
            modifier = outerModifier.padding(
                start = (padding?.start ?: 0).dp,
                top = (padding?.top ?: 0).dp,
                end = (padding?.end ?: 0).dp,
                bottom = (padding?.bottom ?: 0).dp
            )
        ) {
            content()
        }
    }
}

private fun String.toComposeColor(): Color? {
    return runCatching {
        val sanitized = removePrefix("#")
        val value = sanitized.toLong(16)
        when (sanitized.length) {
            6 -> Color((0xFF000000 or value).toInt())
            8 -> Color(value.toInt())
            else -> Color.Unspecified
        }
    }.getOrNull()?.takeIf { it != Color.Unspecified }
}

@Composable
fun ArchitectQuickAction(label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(ArchitectTeal.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Lock, contentDescription = null, tint = ArchitectPrimary)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = ArchitectTextSecondary
        )
    }
}
