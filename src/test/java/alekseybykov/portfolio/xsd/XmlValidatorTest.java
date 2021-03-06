package alekseybykov.portfolio.xsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Aleksey Bykov
 * @since 25.10.2019
 */
@DisplayName("Tests for XML documents validation by XSD schemas")
class XmlValidatorTest {

    @Test
    @DisplayName("Validate xsd:double")
    void testValidateXsdDoubleType() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/simpletype/validation/double.xsd"),
                getFile("schema/simpletype/validation/double_valid.xml")
            )
        );

        assertFalse(
            XmlValidator.validate(
                getFile("schema/simpletype/validation/double.xsd"),
                getFile("schema/simpletype/validation/double_novalid.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate XSD base type restriction")
    void testValidateXsdBaseTypeRestriction() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/simpletype/content/restriction/base_type_restriction.xsd"),
                getFile("schema/simpletype/content/restriction/base_type_restriction_valid.xml")
            )
        );

        assertFalse(
            XmlValidator.validate(
                getFile("schema/simpletype/content/restriction/base_type_restriction.xsd"),
                getFile("schema/simpletype/content/restriction/base_type_restriction_novalid.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate XSD base type restriction with regex pattern")
    void testValidateXsdBaseTypeRestrictionWithRegexPattern() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/simpletype/content/restriction/pattern_restriction.xsd"),
                getFile("schema/simpletype/content/restriction/pattern_restriction_valid.xml")
            )
        );

        assertFalse(
            XmlValidator.validate(
                getFile("schema/simpletype/content/restriction/pattern_restriction.xsd"),
                getFile("schema/simpletype/content/restriction/pattern_restriction_novalid.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate XSD base type list restriction")
    void testValidateXsdListRestriction() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/simpletype/content/list/list_restriction.xsd"),
                getFile("schema/simpletype/content/list/list_restriction_valid.xml")
            )
        );

        assertFalse(
            XmlValidator.validate(
                getFile("schema/simpletype/content/list/list_restriction.xsd"),
                getFile("schema/simpletype/content/list/list_restriction_novalid.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate XSD union restriction")
    void testValidateXsdUnionRestriction() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/simpletype/content/union/union_enum.xsd"),
                getFile("schema/simpletype/content/union/union_enum_valid.xml")
            )
        );

        assertFalse(
            XmlValidator.validate(
                getFile("schema/simpletype/content/union/union_enum.xsd"),
                getFile("schema/simpletype/content/union/union_enum_novalid.xml")
            )
        );

        assertFalse(
            XmlValidator.validate(
                getFile("schema/simpletype/content/union/union_enum.xsd"),
                getFile("schema/simpletype/content/union/union_list_novalid.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate complex XSD types")
    void testValidateComplexXsdType() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/complextype/catalog/CatalogType.xsd"),
                getFile("schema/complextype/Catalog.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate mixing text with elements")
    void testValidateMixedMode() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/complextype/letter/Letter.xsd"),
                getFile("schema/complextype/Letter.xml")
            )
        );
    }

    @Test
    @DisplayName("Validate inheritance")
    void testValidateInheritance() {
        assertTrue(
            XmlValidator.validate(
                getFile("schema/shop/Orders.xsd"),
                getFile("schema/shop/Orders.xml")
            )
        );
    }

    private File getFile(String fileName) {
        return Paths.get("src", "test", "resources").resolve(fileName).toFile();
    }
}
